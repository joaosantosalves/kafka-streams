import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Properties;
import java.util.UUID;

class KafkaStreamProducerTest {

    @BeforeAll
    void setUp() {

        Client client = new Client();
        client.setClientId(UUID.fromString(UUID.randomUUID().toString()));
        client.setIp("134.201.250.155");
        client.setTimestamp(System.currentTimeMillis());

        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    }

    @Test
    void sendToConsumerSuccessful() {

    }
}
