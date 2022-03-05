package com.xrevs.producer;

import com.xrevs.producer.producers.TestProducer;

import java.util.Scanner;

public class Application {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            TestProducer producer = new TestProducer();
            producer.sendMessage(readStdIn());
        }
    }

    private static String readStdIn() {
        return scanner.nextLine();
    }
}
