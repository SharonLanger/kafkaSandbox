package com.kafka.sandbox.entity;

import lombok.Data;

@Data
public class Point {
    private Integer x;
    private Integer y;
    private Boolean lastPoint;
}
