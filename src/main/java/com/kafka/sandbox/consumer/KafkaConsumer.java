package com.kafka.sandbox.consumer;

import com.kafka.sandbox.entity.Point;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    private static final String TOPIC = "userTopic";

    @KafkaListener(
            id = TOPIC + "0",
            groupId = "group_1",
            containerFactory = "concurrentKafkaListenerContainerFactory",
            topicPartitions = { @TopicPartition(topic = TOPIC, partitions = {"0"})}
            )
    public void readPointPartition0(
            Point point,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {

        log.info("partition: {}. {}", partition, point.toString());
    }

    @KafkaListener(
            id = TOPIC + "1",
            groupId = "group_1",
            containerFactory = "concurrentKafkaListenerContainerFactory",
            topicPartitions = { @TopicPartition(topic = TOPIC, partitions = {"1"})}
    )
    public void readPointPartition1(
            Point point,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {

        log.info("partition: {}. {}", partition, point.toString());
    }

}


