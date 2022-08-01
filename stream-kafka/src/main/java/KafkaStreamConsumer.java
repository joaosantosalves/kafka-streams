import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.streams.StreamsConfig;

import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class KafkaStreamConsumer {
    public static void main(String[] args) {
        var consume = new KafkaConsumer<String, String>(properties());
        ArrayList<String> storage = new ArrayList();
        consume.subscribe(Collections.singletonList("KAFKA_STREAMS_NEW_TESTER"));
        while(true) {
            var records = consume.poll(Duration.ofMinutes(30));
            if (!records.isEmpty()) {
                records.forEach(record -> {
                    Client client = new Gson().fromJson(record.value(), Client.class);
                    if(storage.isEmpty()){
                        storage.add(KafkaStreamStorage.kafkaStore(client.getIp()));
                        try {
                            System.out.println(ApiResponse.api(record.value()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        var storageNew = storage.stream()
                                .filter(s -> s.equals(client.getIp()))
                                .collect(Collectors.toList());
                        if (storageNew.isEmpty()){
                            storage.add(KafkaStreamStorage.kafkaStore(client.getIp()));
                            try {
                                System.out.println(ApiResponse.api(record.value()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }
    public static Properties properties(){
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, KafkaStreamProducer.class.getName());
        properties.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 10 * 1024 * 1024L);
        properties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 60000);
        return properties;
    }
}
