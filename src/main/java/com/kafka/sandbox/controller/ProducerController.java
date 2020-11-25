package com.kafka.sandbox.controller;

import com.kafka.sandbox.entity.Point;
import com.kafka.sandbox.producer.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.IntStream;

@RestController
@RequestMapping("producer")
@Slf4j
public class ProducerController {

    private static final int POINTS_NUMBER = 10;

    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    private Point point;

    @GetMapping("/helloProducer/{msg}")
    public String helloProducer(@PathVariable("msg") String msg) {

        log.info("Hello producer: " + msg);

        return "Hello producer";
    }

    @PostMapping("/publishPoints")
    public String publishPoints() {

        IntStream.rangeClosed(1, POINTS_NUMBER).forEach(
                i -> kafkaProducer.insertPoint(point.createPoint())
        );

        return "Published successfully";
    }
}
