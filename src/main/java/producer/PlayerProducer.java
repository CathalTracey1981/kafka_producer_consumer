package producer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.serialization.StringSerializer;
import player.Player;

import java.util.Properties;

import static properties.KafkaProperties.*;

public class PlayerProducer {

    public static Producer<String, Player> createProducer(){
        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        producerProps.put(ProducerConfig.CLIENT_ID_CONFIG, GROUP_ID);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        producerProps.put(SCHEMA_REGISTY_URL, SCHEMA_URL);
        return new KafkaProducer<>(producerProps);
    }
}
