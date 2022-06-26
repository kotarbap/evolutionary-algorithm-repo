package app;

import data.GenerateData;
import data.ReadGenomeFromFile;
import data.SaveData;
import model.Individual;
import model.Point;
import model.Simulation;

import java.io.FileNotFoundException;

public class SalesmanProblemSolution {
    public static void main(String[] args) throws FileNotFoundException {
        Point[] points = new Point[40]; //okresl ilosc miast do odwiedzenia
        //GenerateData.generateRegularPolygon(points, 50, 50, 50); //tutaj mozesz wygenerowac nowy wielokat foremny dom pliku
        //GenerateData.generatePoints(points); //tutaj generowanie nowych losowych nieregularnych punktow
        //SaveData.saveIndividual(new Individual(points), "geny.dat"); //zapis konfiguracji aby moc wykonac pare symulacji dla takich samych danych
        points = ReadGenomeFromFile.readGenomeFromFile(); //wczytywanie miast z pliku aby moc przeprowadzic kilka symulacji na takich samych miastach
        int numberOfGenerations = 1000; //liczba pokolen
        int populationSize = 10; //liczba osobnikow w pokoleniu

        Simulation simulation = new Simulation(numberOfGenerations, populationSize, points);
        simulation.simulate();

    }
}
