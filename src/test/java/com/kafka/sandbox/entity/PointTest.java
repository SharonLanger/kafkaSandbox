package com.kafka.sandbox.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.kafka.sandbox.entity.Point.getDistance;

class PointTest {

    @Autowired
    Point point1;

    @Autowired
    Point point2;

    @Autowired
    Point point3;

    @BeforeEach
    void setUp() {
        point1 = new Point(10,10);
        point2 = new Point(13,14);
        point3 = new Point(7,6);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getDistanceAbs() {
        Assertions.assertThat(getDistance(point1, point2)).isEqualTo(25);
        Assertions.assertThat(getDistance(point1, point3)).isEqualTo(25);
        Assertions.assertThat(getDistance(point2, point3)).isEqualTo(100);
    }
}