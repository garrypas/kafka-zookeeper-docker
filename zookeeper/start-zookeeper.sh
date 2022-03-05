#!/bin/bash
set -e

echo $ZK_MYID > /var/lib/zookeeper/myid

/usr/local/zookeeper/bin/zkServer.sh start-foreground