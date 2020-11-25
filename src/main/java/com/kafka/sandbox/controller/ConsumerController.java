package com.kafka.sandbox.controller;

import com.kafka.sandbox.consumer.KafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consumer")
@Slf4j
public class ConsumerController {

    @Autowired
    KafkaConsumer kafkaConsumer;

    @GetMapping("/getResults")
    public String getResults() {
        log.info(kafkaConsumer.getResults().toString());
        return kafkaConsumer.getResults().toString();
    }
}
