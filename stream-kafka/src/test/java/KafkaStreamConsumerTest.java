import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.streams.StreamsConfig;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

class KafkaStreamConsumerTest {
    void sendToConsumerSuccessful() throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, KafkaStreamProducer.class.getName());
        properties.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 10 * 1024 * 1024L);
        properties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 60000);

        String topic = "KAFKA_STREAMS_NEW_TESTER";
        MockProducer producer = new MockProducer();
        ProducerRecord<byte[], byte[]> record = new ProducerRecord<byte[], byte[]>(topic, "key".getBytes(), "value".getBytes());
        Future<RecordMetadata> metadata = producer.send(record);
        assertEquals(topic, metadata.get().topic());
    }

}
