FROM alpine:3.14

RUN apk update

WORKDIR tmp
RUN apk update
RUN apk add bash

# Install Java
RUN apk add openjdk11-jre
ENV JAVA_HOME "/usr/bin"

# Required ps for Zookeeper (otherwise zkServer shell command gives spurious errors because Alpine uses BusyBox Commands)
RUN apk --no-cache add procps

# Install Zookeeper
RUN wget https://dlcdn.apache.org/zookeeper/zookeeper-3.6.3/apache-zookeeper-3.6.3-bin.tar.gz
RUN tar -zxf apache-zookeeper-3.6.3-bin.tar.gz
RUN cp -R apache-zookeeper-3.6.3-bin /usr/local/zookeeper/
RUN mkdir /app
COPY ./start-zookeeper.sh /app/
RUN chmod +x /app/start-zookeeper.sh

#Configure Zookeeper using defaults
RUN mkdir /var/lib/zookeeper
COPY ./zoo.cfg /usr/local/zookeeper/conf/zoo.cfg

RUN rm -rf apache-zookeeper-3.6.3-bin.tar.gz && rm -rf apache-zookeeper-3.6.3-bin

WORKDIR /usr/local/zookeeper/bin

ENTRYPOINT [ "bash", "/app/start-zookeeper.sh" ]
# ENTRYPOINT ["tail", "-f", "/dev/null"]