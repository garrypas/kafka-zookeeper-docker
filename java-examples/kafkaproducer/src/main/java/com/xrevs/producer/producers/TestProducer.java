package com.xrevs.producer.producers;

import lombok.var;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.serialization.VoidSerializer;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.apache.kafka.clients.producer.ProducerConfig.ACKS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.CLIENT_ID_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.LINGER_MS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.MAX_BLOCK_MS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

public class TestProducer {
    private final Producer producer;

    public TestProducer() {
        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9101");
        props.put(KEY_SERIALIZER_CLASS_CONFIG, VoidSerializer.class.getName());
        props.put(CLIENT_ID_CONFIG, UUID.randomUUID().toString());
        props.put(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(LINGER_MS_CONFIG, 0);
        props.put(MAX_BLOCK_MS_CONFIG, "2000");
        props.put(ACKS_CONFIG, "all");
        producer = new KafkaProducer<String, String>(props);
    }

    public void sendMessage(String message) {
        System.out.println("Sending...");
        var record = new ProducerRecord("test", message);
        try {
            var res = producer.send(record);
            res.get(3000, TimeUnit.MILLISECONDS);
            producer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
