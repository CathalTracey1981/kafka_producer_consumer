Steps to use this app

1. Download confluent from here: https://www.confluent.io/download/

2. From the confluent folder run: ./bin/confluent local start

3. Then run this command to listen to the correct topic: ./bin/kafka-avro-console-consumer --bootstrap-server localhost:9092 --topic PLAYER.TOPIC

4. Run the main method from Application