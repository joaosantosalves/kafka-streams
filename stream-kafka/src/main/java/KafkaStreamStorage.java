import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;

public class KafkaStreamStorage {
    public static String kafkaStore(String ip){
        StoreBuilder kafkaStoreBuilder = Stores.keyValueStoreBuilder(Stores.persistentKeyValueStore(ip),
                                Serdes.String(),
                                Serdes.Long())
                        .withCachingEnabled();
        return kafkaStoreBuilder.name();
    }
}
