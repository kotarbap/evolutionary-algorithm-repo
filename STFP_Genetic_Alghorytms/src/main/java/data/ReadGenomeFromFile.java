package data;

import model.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class ReadGenomeFromFile {
    public static Point[] readGenomeFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("geny.dat"));
        LinkedList<Point> list = new LinkedList<>();
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] words = line.split("\\s+");
            double x = Double.parseDouble(words[0]);
            double y = Double.parseDouble(words[1]);
            list.add(new Point(x, y));
        }
        Point[] points = new Point[list.size()];
        list.toArray(points);
        return points;
    }
}
