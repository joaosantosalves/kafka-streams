import com.google.gson.Gson;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class KafkaStreamProducer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var producer = new KafkaProducer<String, String>(properties());
        Client client = new Client();
        client.setClientId(UUID.fromString(UUID.randomUUID().toString()));
        client.setIp("134.201.250.155");
        client.setTimestamp(System.currentTimeMillis());

        var record = new ProducerRecord<>("KAFKA_STREAMS_NEW_TESTER", UUID.randomUUID().toString(), new Gson().toJson(client));
        producer.send(record, (data, ex) ->{
            if(ex != null){
                ex.printStackTrace();
                return;
            }
            System.out.println("Success");
        }).get();
    }

    public static Properties properties(){
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
