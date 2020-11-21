package com.kafka.sandbox.producer;

import com.kafka.sandbox.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final String TOPIC = "userTopic";

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;


    public void writeMsg(String msg) {

        System.out.println("writeMsg:" + msg);
        kafkaTemplate.send(TOPIC, new User(msg, 18));
    }
}
