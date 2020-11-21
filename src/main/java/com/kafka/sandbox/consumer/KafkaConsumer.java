package com.kafka.sandbox.consumer;

import com.kafka.sandbox.entity.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final String TOPIC = "userTopic";

    @KafkaListener(topics = TOPIC, groupId = "group_1", containerFactory = "concurrentKafkaListenerContainerFactory")
    public void readMsg(User user) {

        System.out.println("KafkaConsumer:");
        System.out.println("Name:" + user.getName());
        System.out.println("Age:" + user.getAge());
    }
}

