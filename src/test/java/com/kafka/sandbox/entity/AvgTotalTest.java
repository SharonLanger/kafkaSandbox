package com.kafka.sandbox.entity;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.AfterEach;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AvgTotalTest {

    private AvgTotal avgTotal = new AvgTotal();

    @Test
    void addAvgToTotal() {
        Assertions.assertThat(avgTotal.getAvgPoint1().getAvgWeight())
                .isEqualTo(0);
        Assertions.assertThat(avgTotal.getAvgPoint2().getAvgWeight())
                .isEqualTo(0);
        avgTotal.addAvgToTotal(1.0, 1.0,3,1);
        avgTotal.addAvgToTotal(1.0, 1.0,5,2);

        Assertions.assertThat(avgTotal.getAvgPoint1().getAvgWeight())
                .isEqualTo(3);
        Assertions.assertThat(avgTotal.getAvgPoint2().getAvgWeight())
                .isEqualTo(5);

        Assertions.assertThat(avgTotal.getAvgPoint1().getX())
                .isEqualTo(1.0);
        Assertions.assertThat(avgTotal.getAvgPoint1().getY())
                .isEqualTo(1.0);
        Assertions.assertThat(avgTotal.getAvgPoint2().getX())
                .isEqualTo(1.0);
        Assertions.assertThat(avgTotal.getAvgPoint2().getY())
                .isEqualTo(1.0);

        avgTotal.addAvgToTotal(3.0, 1.0,2,1);
        avgTotal.addAvgToTotal(11.0, 5.0,12,2);

        Assertions.assertThat(avgTotal.getAvgPoint1().getAvgWeight())
                .isEqualTo(5);
        Assertions.assertThat(avgTotal.getAvgPoint2().getAvgWeight())
                .isEqualTo(17);

        Assertions.assertThat(avgTotal.getAvgPoint1().getX())
                .isEqualTo(1.8);
        Assertions.assertThat(avgTotal.getAvgPoint1().getY())
                .isEqualTo(1.0);

        Assertions.assertThat(avgTotal.getAvgPoint2().getX())
                .isCloseTo(8.058, Percentage.withPercentage(0.1));
        Assertions.assertThat(avgTotal.getAvgPoint2().getY())
                .isCloseTo(3.823, Percentage.withPercentage(0.1));
    }
}