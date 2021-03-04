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

    private static final int POINTS_NUMBER = 100000;

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
                i -> kafkaProducer.insertPoint(point.createRandomPoint(false))
        );

        // Send last point notifications to both partitions
        kafkaProducer.insertPoint(new Point(0.0, 0.0, true));
        kafkaProducer.insertPoint(new Point(99.0, 99.0, true));

        return "Published successfully";
    }

    @PostMapping("/publishPointsTestPartition0")
    public String publishPointsTestPartition0() {

        IntStream.rangeClosed(1, POINTS_NUMBER).forEach(
                i -> kafkaProducer.insertPoint(new Point(5,5))
        );

        // Send last point notifications to both partitions
        kafkaProducer.insertPoint(new Point(0.0, 0.0, true));
        kafkaProducer.insertPoint(new Point(99.0, 99.0, true));

        return "Published successfully";
    }

    @PostMapping("/publishPointsTestPartition1")
    public String publishPointsTestPartition1() {

        IntStream.rangeClosed(1, POINTS_NUMBER).forEach(
                i -> kafkaProducer.insertPoint(new Point(60,60))
        );

        // Send last point notifications to both partitions
        kafkaProducer.insertPoint(new Point(0.0, 0.0, true));
        kafkaProducer.insertPoint(new Point(99.0, 99.0, true));

        return "Published successfully";
    }
}
