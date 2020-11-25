package com.kafka.sandbox.consumer;

import com.kafka.sandbox.entity.AvgTotal;
import com.kafka.sandbox.entity.Point;
import com.kafka.sandbox.service.PointKmins;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @Autowired
    private AvgTotal avgTotal;

    private PointKmins pointKmins1 = new PointKmins(new Point(1,1),new Point(50,50));
    private PointKmins pointKmins2 = new PointKmins(new Point(1,1),new Point(50,50));


    private static final String TOPIC = "userTopic";

    @KafkaListener(
            id = TOPIC + "0",
            groupId = "group_1",
            containerFactory = "concurrentKafkaListenerContainerFactory",
            topicPartitions = {@TopicPartition(topic = TOPIC, partitions = {"0"})}
    )
    public void readPointPartition0(
            Point point,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {

        log.debug("partition: {}. {}", partition, point.toString());

        if ( !point.getLastPoint() ) {
            pointKmins1.updateAvgPoint(point);
        } else {
            log.info("Finished calculating average in partition 0");
            log.info("===========================================");
            log.info("      Point group 1: x={}, y={}, total points: {}" ,
                    pointKmins1.getAvgPoint1().getX(),
                    pointKmins1.getAvgPoint1().getY(),
                    pointKmins1.getAvgPoint1().getAvgPointsNumber());
            log.info("      Point group 2: x={}, y={}, total points: {}" ,
                    pointKmins1.getAvgPoint2().getX(),
                    pointKmins1.getAvgPoint2().getY(),
                    pointKmins1.getAvgPoint2().getAvgPointsNumber());
        }

    }

    @KafkaListener(
            id = TOPIC + "1",
            groupId = "group_1",
            containerFactory = "concurrentKafkaListenerContainerFactory",
            topicPartitions = {@TopicPartition(topic = TOPIC, partitions = {"1"})}
    )
    public void readPointPartition1(
            Point point,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {

        log.debug("partition: {}. {}", partition, point.toString());

        if ( !point.getLastPoint() ) {
            pointKmins2.updateAvgPoint(point);
        } else {
            log.info("Finished calculating average in partition 1");
            log.info("===========================================");
            log.info("      Point group 1: x={}, y={}, total points: {}" ,
                    pointKmins2.getAvgPoint1().getX(),
                    pointKmins2.getAvgPoint1().getY(),
                    pointKmins2.getAvgPoint1().getAvgPointsNumber());
            log.info("      Point group 2: x={}, y={}, total points: {}" ,
                    pointKmins2.getAvgPoint2().getX(),
                    pointKmins2.getAvgPoint2().getY(),
                    pointKmins2.getAvgPoint2().getAvgPointsNumber());
        }

    }

}


