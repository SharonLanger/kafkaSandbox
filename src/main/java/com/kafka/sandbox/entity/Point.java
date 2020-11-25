package com.kafka.sandbox.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

import static java.lang.Math.abs;

@Data
@Service
@NoArgsConstructor
public class Point {
    private Double x;
    private Double y;
    private Boolean lastPoint;

    private static final Integer RANDOM_BOUND = 100;

    public Point(Double x,
                 Double y) {
        this.x = x;
        this.y = y;
        this.lastPoint = false;
    }

    public Point(Integer x,
                 Integer y) {
        this(new Double(x), new Double(y));
    }

    public Point(Double x,
                 Double y,
                 Boolean lastPoint) {
        this.x = x;
        this.y = y;
        this.lastPoint = lastPoint;
    }

    public Point(Point point) {
        this(point.x, point.y, point.lastPoint);
    }

    public static Double getDistance(Point point1, Point point2) {
        Double x_diff = abs(point1.x - point2.x);
        Double y_diff = abs(point1.y - point2.y);
        return x_diff * x_diff + y_diff * y_diff;
    }

    public Point createRandomPoint(Boolean lastPoint) {
        Random rand = new Random();
        return new Point(rand.nextInt(RANDOM_BOUND) / 1.0, rand.nextInt(RANDOM_BOUND) / 1.0, lastPoint);
    }
}
