package com.kafka.sandbox.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Data
@Service
@NoArgsConstructor
public class Point {
    private Integer x;
    private Integer y;
    private Boolean lastPoint;

    private static final Integer RANDOM_BOUND = 100;

    public Point(Integer x, Integer y, Boolean lastPoint) {
        this.x = x;
        this.y = y;
        this.lastPoint = lastPoint;
    }

    public Point createPoint() {
        Random rand = new Random();
        return new Point(rand.nextInt(RANDOM_BOUND),rand.nextInt(RANDOM_BOUND),true);
    }
}
