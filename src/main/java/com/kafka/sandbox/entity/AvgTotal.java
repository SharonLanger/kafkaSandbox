package com.kafka.sandbox.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AvgTotal {

    private AvgPoint avgPoint1 = new AvgPoint(0);
    private AvgPoint avgPoint2 = new AvgPoint(0);

    public void addAvgToTotal(AvgPoint avgPoint, int avgPointId) {
        if ( avgPointId == 1 ) {
            avgPoint1.setNewAvg(avgPoint);
        } else {
            avgPoint2.setNewAvg(avgPoint);
        }
    }

    private class AvgPoint extends Point{
        Integer avgWeight;

        public AvgPoint(Integer avgWeight) {
            super(0,0);
            this.avgWeight = avgWeight;
        }

        public AvgPoint(Double x, Double y, Integer avgWeight) {
            super(x, y);
            this.avgWeight = avgWeight;
        }

        private void setNewAvg(AvgPoint newPoint) {
            this.setX(
                    (this.avgWeight * this.getX() + newPoint.getX()) /
                            (this.avgWeight + newPoint.avgWeight)
            );
            this.setY(
                    (this.avgWeight * this.getY() + newPoint.getY()) /
                            (this.avgWeight + newPoint.avgWeight)
            );
            this.avgWeight = this.avgWeight + newPoint.avgWeight;
        }

    }
}
