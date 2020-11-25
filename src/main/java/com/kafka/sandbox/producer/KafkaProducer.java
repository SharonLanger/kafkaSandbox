package com.kafka.sandbox.producer;

import com.kafka.sandbox.entity.Point;
import com.kafka.sandbox.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {

    @Value("${topic.name}")
    private String TOPIC;

    @Autowired
    private KafkaTemplate<String, Point> kafkaTemplate;


    public void insertPoint(Point point) {

        log.info("InsertPoint:");
        kafkaTemplate.send(TOPIC, getPartition(point), String.valueOf(point.hashCode()), point);
        log.info(point.toString());
    }

    private Integer getPartition(Point point) {
        return point.getX() <  50 ? 0 : 1;
    }
}
