#!/bin/bash
set -e

# This script will
# - scale up zookeeper to a multipe servers in an ensemble, adding the necessary config to each container
# - scale up Kafka to have more brokers

ZK_SCALE=3
KAFKA_SCALE=3

docker-compose up --scale zookeeper=$ZK_SCALE --scale kafka=$KAFKA_SCALE --detach

# for z in {1..3}
for (( z=1; z<=$ZK_SCALE; z++ ))
do
    # for n in {1..3}
    for (( n=1; n<=$ZK_SCALE; n++ ))
    do
        docker exec -t "kafka-zookeeper-docker_zookeeper_$z" /bin/bash -c "echo 'server.$n=kafka-zookeeper-docker_zookeeper_$n:2888:3888' >> /usr/local/zookeeper/conf/zoo.cfg"
    done
    docker exec -t "kafka-zookeeper-docker_zookeeper_$z" /bin/bash -c "echo $z > /var/lib/zookeeper/myid"
    docker exec -t "kafka-zookeeper-docker_zookeeper_$z" /bin/bash -c "/usr/local/zookeeper/bin/zkServer.sh restart"
done
