package com.kafka.sandbox.controller;

import com.kafka.sandbox.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("producer")
public class ProducerController {

    @Autowired
    KafkaProducer kafkaProducer;

    @GetMapping("/helloProducer/{msg}")
    public String helloProducer(@PathVariable("msg") String msg) {

        System.out.println("Hello producer: " + msg);
        return "Hello producer";
    }

    @PostMapping("/{msg}")
    public String publishMsg(@PathVariable("msg") String msg) {

        kafkaProducer.writeMsg(msg);

        return "Published successfully";
    }
}
