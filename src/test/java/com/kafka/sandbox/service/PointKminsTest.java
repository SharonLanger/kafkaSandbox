package com.kafka.sandbox.service;

import com.kafka.sandbox.entity.Point;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class PointKminsTest {

    PointKmins pointKmins;

    @Autowired
    Point pointWeight;

    @Autowired
    Point point;

    @BeforeEach
    void setUp() {
        pointWeight = new Point(10,10);
        pointKmins = new PointKmins(pointWeight, new Point(50,50));
        pointKmins.updateAvgPoint(pointWeight);
        pointKmins.updateAvgPoint(pointWeight);
        pointKmins.updateAvgPoint(pointWeight);
        point = new Point(15, 10);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkSetUpIsOK() {
        log.info("getAvgPointsNumber1: {}", pointKmins.getAvgPoint1().getAvgPointsNumber());
        log.info("getAvgPointsNumber2: {}", pointKmins.getAvgPoint2().getAvgPointsNumber());
        Assertions.assertThat(pointKmins.getAvgPoint1().getX()).isEqualTo(pointWeight.getX());
        Assertions.assertThat(pointKmins.getAvgPoint1().getY()).isEqualTo(pointWeight.getY());
        Assertions.assertThat(pointKmins.getAvgPoint1().getAvgPointsNumber()).isEqualTo(4);
        Assertions.assertThat(pointKmins.getAvgPoint2().getX()).isEqualTo(50);
        Assertions.assertThat(pointKmins.getAvgPoint2().getY()).isEqualTo(50);
    }

    @Test
    void averageCalculationPoint1() {
        pointKmins.updateAvgPoint(point);
        Assertions.assertThat(pointKmins.getAvgPoint1().getX())
                .isEqualTo(11);
        Assertions.assertThat(pointKmins.getAvgPoint1().getY())
                .isEqualTo(10);
        Assertions.assertThat(pointKmins.getAvgPoint1().getAvgPointsNumber())
                .isEqualTo(5);
        Assertions.assertThat(pointKmins.getAvgPoint2().getX())
                .isEqualTo(50);
        Assertions.assertThat(pointKmins.getAvgPoint2().getY())
                .isEqualTo(50);
        Assertions.assertThat(pointKmins.getAvgPoint2().getAvgPointsNumber())
                .isEqualTo(1);
    }

    @Test
    void averageCalculationPoint2() {
        pointKmins.updateAvgPoint(new Point(60, 60));
        Assertions.assertThat(pointKmins.getAvgPoint2().getX())
                .isEqualTo(55);
        Assertions.assertThat(pointKmins.getAvgPoint2().getY())
                .isEqualTo(55);
        Assertions.assertThat(pointKmins.getAvgPoint2().getAvgPointsNumber())
                .isEqualTo(2);
    }
}