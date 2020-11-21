package com.kafka.sandbox.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final String TOPIC = "firstTopic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public void writeMsg(String msg) {

        System.out.println("writeMsg:" + msg);
        kafkaTemplate.send(TOPIC, msg);
    }
}
