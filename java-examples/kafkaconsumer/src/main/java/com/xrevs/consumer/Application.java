package com.xrevs.consumer;

import com.xrevs.consumer.consumers.TestConsumer;

public class Application {
    public static void main(String[] args) {
        TestConsumer consumer = new TestConsumer();
        consumer.consume();
        System.out.println("Done.");
    }
}
