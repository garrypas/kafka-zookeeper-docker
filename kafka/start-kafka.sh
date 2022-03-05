#!/bin/bash

set -e

sed -i "s/\$KAFKA_BROKER_ID/$KAFKA_BROKER_ID/g" /usr/local/kafka/config/server.properties
sed -i "s/\$KAFKA_EXTERNAL_PORT/$KAFKA_EXTERNAL_PORT/g" /usr/local/kafka/config/server.properties
sed -i "s/\$KAFKA_EXTERNAL_HOST/$KAFKA_EXTERNAL_HOST/g" /usr/local/kafka/config/server.properties
sed -i "s/\$KAFKA_ZOOKEEPER_CONNECT/$KAFKA_ZOOKEEPER_CONNECT/g" /usr/local/kafka/config/server.properties

/usr/local/kafka/bin/kafka-server-start.sh /usr/local/kafka/config/server.properties
