package com.kafka.sandbox.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final String TOPIC = "firstTopic";

    @KafkaListener(topics = TOPIC, groupId = "group_1")
    public void readMsg(String msg) {

        System.out.println("KafkaConsumer: " + msg);

    }
}

