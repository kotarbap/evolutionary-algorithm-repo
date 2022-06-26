package data;

import model.Point;

import java.util.*;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class GenerateData {
    static private final Random random = new Random();

    static public void generatePoints(Point[] points) {
        int x, y;
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < points.length; ++i) {
            x = random.nextInt(0, 101);
            y = random.nextInt(0, 101);

            while (isDrawn(map, x, y)) {
                x = random.nextInt(0, 101);
                y = random.nextInt(0, 101);
            }

            if (map.containsKey(x)) {
                List<Integer> toChange = map.get(x);
                toChange.add(y);
                map.replace(x, toChange);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(y);
                map.put(x, list);
            }

            points[i] = new Point(x, y);
        }
    }

    static public void generateRegularPolygon(Point[] points, int x_centre, int y_centre, double radius) {
        double x, y;
        for (int i = 0; i < points.length; ++i) {
            x = radius * cos(2 * Math.PI * i / points.length) + x_centre;
            y = radius * sin(2 * Math.PI * i / points.length) + y_centre;
            points[i] = new Point(x, y);
        }

        randomSwapPoints(points, random);
    }

    public static void randomSwapPoints(Point[] points, Random random) {
        for (int i = 0; i < points.length * 100; ++i) {
            int j = random.nextInt(0, points.length);
            int k = random.nextInt(0, points.length);
            Collections.swap(Arrays.asList(points), j, k);
        }
    }

    static private boolean isDrawn(Map<Integer, List<Integer>> map, int x, int y) {
        if (map.containsKey(x)) {
            return map.get(x).contains(y);
        }
        return false;
    }
}
