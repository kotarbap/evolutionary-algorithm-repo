package data;

import model.Individual;
import model.Point;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaveData {
    static public void saveIndividual(Individual individual, String fileName) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(fileName);
        Point[] points = individual.getPoints();
        for (Point point : points) {
            printWriter.println(point);
        }
        printWriter.println(points[0]);
        printWriter.close();
        saveGnuplotScript(individual);
    }

    static public void saveGnuplotScript(Individual individual) throws FileNotFoundException {
        String script = "set title \"Wizualizacja osobnika na ukladzie wspolrzednych\"\n" +
                String.format("set xlabel \"Calkowita dlugosc osobnika: %.2f\"\n", individual.getLength()) +
                "plot \"najlepszyosobnik.dat\" with lines lc rgb \"blue\" title \"\",\\\n" +
                "\"najlepszyosobnik.dat\" with points title \"\" pointtype 7 pointsize 1.0 lc rgb \"red\"\n";
        PrintWriter printWriter = new PrintWriter("skrypt.txt");
        printWriter.println(script);
        printWriter.close();
    }

    static  public void saveCSVFile(double[] bestInGeneration, double[] averageInGeneration,
                                    double[] worstInGeneration) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("dane.csv");
        printWriter.println("nr_pokolenia najlepszy_fitness sredni_fitness najgorszy_fitness");
        for (int i = 0; i < bestInGeneration.length; ++i) {
            printWriter.println((i + 1) + " " + String.format("%.2f", (1.0 / bestInGeneration[i]) * 10_000) + " " +
                    String.format("%.2f", (1.0 / averageInGeneration[i]) * 10_000) + " " +
                    String.format("%.2f", (1.0 / worstInGeneration[i] * 10_000)));
        }
        printWriter.close();
    }
}
