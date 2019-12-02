## How to run this project?

1. Download [Zookeeper](https://www.apache.org/dyn/closer.cgi/zookeeper/) and unpack it
2. Download [Apache Kafka](https://kafka.apache.org/downloads) binary and unpack it
3. Run Zookeeper
`./apache-zookeeper-3.5.6-bin/bin/zkServer.sh start`
4. Run Apache Kafka server
`./bin/kafka-server-start.sh ./config/server.properties`
5. Create `server.message` topic
`./bin/kafka-topics.sh --create --zookeeper localhost:2181 --partitions 1 --replication-factor 1 --topic server.message`
6. Make POST request with body `{ "author": "foo", "message": "bar" }` to `http://localhost:9000/message`
7. Make GET request to `http://localhost:9100/message`
8. ???
9. PROFIT
