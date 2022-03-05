# Kafka/Zookeeper Docker sandbox

A project to create a local sandbox for experimenting with Kafka/Zookeeper and general learning.

## Create Kafka/Zookeeper

Create Zookeeper and Kafka cluster

```bash
docker-compose build
docker-compose up
```

Alternative use Docker run for more control

### Zookeeper

Setup Docker network
```bash
docker network create \
   -d bridge \
   --gateway 192.168.50.1 \
   --subnet 192.168.50.0/24 \
   network_zookeeper
```

Build and run a Zookeeper instance (zookeeper/zoo.cfg is setup for a 3 host ensemble, expect names zookeeper_1, zookeeper_2, zookeeper_3 - this needs to be edited for a different configuration, with the public port - 2281 in this case - edited to match). Replace $ZK_MYID.

```bash
cd zookeeper
docker build -t zookeeper_example .
docker run \
   --name "zookeeper_$ZK_MYID" \
   -h "zookeeper_$ZK_MYID" \
   -h "zookeeper_$ZK_MYID" \
   -p "228$ZK_MYID:2181" \
   -e ZK_MYID=$ZK_MYID \
   --network network_zookeeper \
   -l zookeeper \
   zookeeper_example
```

### Kafka

Setup Docker network
```bash
docker network create \
   -d bridge \
   --gateway 192.168.51.1 \
   --subnet 192.168.51.0/24 \
   network_kafka
```

Build and run a Kafka instance

```bash
cd kafka
docker build -t kafka_example .
docker run \
   --name kafka_$KAFKA_BROKER_ID \
   -h "kafka_$KAFKA_BROKER_ID" \
   -p "910$KAFKA_BROKER_ID:910$KAFKA_BROKER_ID" \
   -e KAFKA_BROKER_ID=$KAFKA_BROKER_ID \
   -e "KAFKA_EXTERNAL_PORT=910$KAFKA_BROKER_ID" \
   -e KAFKA_EXTERNAL_HOST=localhost \
   -e KAFKA_ZOOKEEPER_CONNECT=172.17.0.1:2281,172.17.0.1:2282,172.17.0.1:2283 \
   -l kafka \
   --network network_kafka \
   kafka_example
```

## Consumer/Producer

There are simple consumer/producer examples in Java and JavaScript included. This allows you to post messages from the command line and watch the consumer consume them.

For Java, this can be run from IntelliJ

For node, run from the CLI using

```
npm run producer
```

... or ...

```
npm run consumer
```
