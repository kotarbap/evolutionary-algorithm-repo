package model;

import static java.lang.Math.*;

public class Point {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceToPoint(Point point) {
        double x = abs(getX() - point.getX());
        double y = abs(getY() - point.getY());
        return sqrt(pow(x, 2) + pow(y, 2));
    }

    public static double calculateLength(Point[] points) {
        double distance = 0;
        for (int i = 0; i < points.length - 1; ++i) {
            distance += points[i].distanceToPoint(points[i + 1]);
        }
        distance += points[points.length - 1].distanceToPoint(points[0]);
        return distance;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

}
