package com.kafka.sandbox.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AvgTotal {

    public AvgPoint avgPoint1;
    public AvgPoint avgPoint2;

    public AvgTotal() {
        this.avgPoint1 = new AvgPoint(0);
        this.avgPoint2 = new AvgPoint(0);
    }

    public void addAvgToTotal(Double x, Double y, int n, int avgPointId) {
        if ( avgPointId == 1 ) {
            avgPoint1.setNewAvg(x, y, n);
        } else {
            avgPoint2.setNewAvg(x, y, n);
        }
    }


    public String toString() {
        String str =
                "Avg point 1: x=" + this.avgPoint1.getX() +
                " y=" + this.avgPoint1.getY() +
                " points: " + this.avgPoint1.getAvgWeight().toString();
        str +=
                "\nAvg point 2: x=" + this.avgPoint2.getX() +
                " y=" + this.avgPoint2.getY() +
                " points: " + this.avgPoint2.getAvgWeight().toString();
        return str;
    }

    @Data
    public static class AvgPoint extends Point{
        Integer avgWeight;

        public AvgPoint(Integer avgWeight) {
            super(0,0);
            this.avgWeight = avgWeight;
        }

        public AvgPoint(Point point, Integer avgWeight) {
            super(point);
            this.avgWeight = avgWeight;
        }

        public AvgPoint(Double x, Double y, Integer avgWeight) {
            super(x, y);
            this.avgWeight = avgWeight;
        }

        private void setNewAvg(Point point, Integer n) {
            this.setNewAvg(point.getX(), point.getY(), n);
        }

        private void setNewAvg(Double x, Double y, Integer n) {
            this.setX(
                    ((this.avgWeight * this.getX()) + (n * x)) /
                            (this.avgWeight + n)
            );
            this.setY(
                    ((this.avgWeight * this.getY()) + (n * y)) /
                            (this.avgWeight + n)
            );
            this.avgWeight = this.avgWeight + n;
        }

    }
}
