package genetics;

import model.Point;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static model.Point.calculateLength;

public class Mutation {
    static private final Random random = new Random();

    static public void randomMutate(Point[] points) {
        int j = random.nextInt(0, points.length);
        int k = random.nextInt(0, points.length);
        Collections.swap(Arrays.asList(points), j, k);
    }

    static public void positiveMutate(Point[] points) {
        double firstLength = Point.calculateLength(points);
        for (int i = 0; i < 100_000; ++i) {
            int j = random.nextInt(0, points.length);
            int k = random.nextInt(0, points.length);
            Collections.swap(Arrays.asList(points), j, k);
            if (calculateLength(points) < firstLength) {
                break;
            } else {
                Collections.swap(Arrays.asList(points), j, k);
            }
        }
    }

    public static void nearestNeighborAlgorithm(Point[] points) {
        int idToCheck = random.nextInt(0, points.length - 1);
        int idToFind = 0;
        double distanceToNextPoint = points[idToCheck].distanceToPoint(points[idToCheck + 1]);
        double theShortest = distanceToNextPoint;

        for (int i = 0; i <points.length - 1; ++i) {
            if (idToCheck != i) {
                double newDistance = points[idToCheck].distanceToPoint(points[i]);
                if (newDistance < distanceToNextPoint && newDistance < theShortest) {
                    idToFind = i;
                    theShortest = newDistance;
                }
            }
        }

        if (distanceToNextPoint > theShortest) {
            Collections.swap(Arrays.asList(points), idToCheck + 1, idToFind);
        }
    }
}
