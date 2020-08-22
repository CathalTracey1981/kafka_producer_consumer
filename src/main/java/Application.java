import consumer.PlayerConsumer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import player.Player;
import producer.PlayerProducer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private final String TOPIC = "PLAYER.TOPIC";

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        logger.info("Starting application....");
        Application application = new Application();
        application.start();
    }

    private void start() throws InterruptedException, ExecutionException, IOException {
        readPlayersFromFileAndWriteToKafka();
        consumePlayersFromKafka();
    }

    private void readPlayersFromFileAndWriteToKafka() throws IOException, InterruptedException, ExecutionException {
        Producer<String, Player> producer = PlayerProducer.createProducer();
        Player player;
        logger.info("Reading players from file");
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/players.csv"));
        String line;
        logger.info("Publishing players to kafka");
        while ((line = reader.readLine()) != null) {
            String[] values = line.split("\\s*,\\s*");
            player = getPlayer(values);

            writeRecordToKafkaTopic(producer, player);
        }
        reader.close();
    }

    private void writeRecordToKafkaTopic(Producer<String, Player> producer, Player player) throws InterruptedException, ExecutionException {
        logger.debug("Writing player to kafka: " + player.getFirstname() + " " + player.getLastname());
        ProducerRecord<String, Player> producerRecord = new ProducerRecord<>(TOPIC, player);
        producer.send(producerRecord).get();
    }

    private void consumePlayersFromKafka() {
        logger.info("Consuming players from kafka topic: " + TOPIC);
        Consumer<String, Player> consumer = PlayerConsumer.createConsumer(TOPIC);
        while (true){
            ConsumerRecords<String, Player> consumerRecord = consumer.poll(1000);
            if (consumerRecord.count() == 0){
                break;
            }
            consumerRecord.forEach(rec -> System.out.println(rec.value()));
        }
    }

    public Player getPlayer(String[] values) {
        return Player.newBuilder()
                .setFirstname(values[0])
                .setLastname(values[1])
                .setPosition(values[2])
                .setTeam(values[3])
                .build();
    }

}
