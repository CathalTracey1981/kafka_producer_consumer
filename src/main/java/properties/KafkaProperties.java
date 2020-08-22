package properties;

public class KafkaProperties {

    public static String TOPIC = "PLAYER.TOPIC";
    public static String BOOTSTRAP_SERVER = "localhost:9092";
    public static String GROUP_ID = "player.client";
    public static String ENABLE_AUTO_COOMIT_FLAG = "false";
    public static String OFFSET = "earliest";
    public static String SCHEMA_REGISTY_URL = "schema.registry.url";
    public static String SCHEMA_URL = "http://127.0.0.1:8081";
    public static String PLAYERS_CSV_PATH = "src/main/resources/players.csv";
    public static String SPLIT_REGEX = "\\s*,\\s*";
}
