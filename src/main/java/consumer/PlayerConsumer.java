package consumer;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import player.Player;

import java.util.Collections;
import java.util.Properties;

import static properties.KafkaProperties.*;

public class PlayerConsumer {

    public static Consumer<String, Player> createConsumer(String topic){
        Properties consumerProps = new Properties();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);
        consumerProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, ENABLE_AUTO_COOMIT_FLAG);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, OFFSET);
        consumerProps.put(SCHEMA_REGISTY_URL, SCHEMA_URL);
        Consumer<String, Player> consumer = new KafkaConsumer<>(consumerProps);
        consumer.subscribe(Collections.singleton(topic));
        return consumer;
    }
}
