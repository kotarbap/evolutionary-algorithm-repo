package model;

import genetics.Mutation;

import java.util.Arrays;

public class Individual implements Comparable<Individual> {
    private final Point[] points;
    private final double length;

    public Individual(Point[] points) {
        this.points = new Point[points.length];
        System.arraycopy(points, 0, this.points, 0, points.length);
        length = Point.calculateLength(points);
    }

    public Point[] getPoints() {
        return Arrays.copyOf(points, points.length);
    }

    public double getLength() {
        return length;
    }

    public Individual createDescendant() {
        Point[] genes = getPoints();
        Mutation.nearestNeighborAlgorithm(genes);
        Mutation.positiveMutate(genes);
        if (Math.random() < 0.2) {
            Mutation.randomMutate(genes);
        }
        return new Individual(genes);
    }

    @Override
    public int compareTo(Individual o) {
        return Double.compare(getLength(), o.getLength());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Point point : points) {
            sb.append(point).append("\n");
        }
        sb.append(points[0]).append("\n");
        sb.append("Length: ").append(length).append("\n");
        return sb.toString();
    }
}
