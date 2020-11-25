package com.kafka.sandbox.service;


import com.kafka.sandbox.entity.Point;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Slf4j
@Component
public class PointKmins {

    // Center of point group 1
    @Autowired
    Point startingPoint1;

    // Center of point group 2
    @Autowired
    Point startingPoint2;

    PointAvg avgPoint1;
    PointAvg avgPoint2;

    public PointKmins(Point point1, Point point2) {
        this.startingPoint1 = point1;
        this.startingPoint2 = point2;
        this.avgPoint1 = new PointAvg(point1, 1);
        this.avgPoint2 = new PointAvg(point2, 1);
    }

    private PointAvg getPointGroupAvg(Point point) {
        return Point.getDistance(startingPoint1, point) < Point.getDistance(startingPoint2, point)
                ? avgPoint1 : avgPoint2;
    }

    public void updateAvgPoint(Point point) {
        PointAvg pointAvg = getPointGroupAvg(point);
        pointAvg.setNewAvgPoint(point);
        log.debug("avgPointsNumber: {}", pointAvg.avgPointsNumber.toString());
    }

    @Data
    public class PointAvg extends Point{
        Integer avgPointsNumber;

        public PointAvg(Point point,
                        Integer avgPointsNumber) {
            super(point);
            this.avgPointsNumber = avgPointsNumber;
        }

        private void setNewAvgPoint(Point point) {
            PointAvg pointAvg = getPointGroupAvg(point);
            pointAvg.setX(
                    (pointAvg.avgPointsNumber * pointAvg.getX() + point.getX()) /
                            (pointAvg.avgPointsNumber + 1)
            );
            pointAvg.setY(
                    (pointAvg.avgPointsNumber * pointAvg.getY() + point.getY()) /
                            (pointAvg.avgPointsNumber + 1)
            );
            pointAvg.setAvgPointsNumber(pointAvg.avgPointsNumber + 1);
        }
    }
}
