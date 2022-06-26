package model;

import data.SaveData;

import java.io.FileNotFoundException;

public class Simulation {
    private final int numberOfGenerations;
    private final int populationSize;
    private final Point[] genePool;

    /**
     * Ponizej program zapisuje do pamieci najlepszego wygenerowanego osobnika ze wszystkich,
     * a nastepnie dla kazdego pokolenia zapisuje najkrotszego osobnika, srednia dlugosc osobnikow
     * oraz najdluzszego osobnika, dane te posluza do wygenerowania wykresow najlepszego, sredniego
     * i najgorszego fitnessu, oczywiscie fitness jest odwrotnie proporcjonalny do dlugosci osobnika,
     * wiec aby najkrotszy osobnik mial najlepszy fitness, przystosowanie powinno byc liczone jako
     * odwrotnosc dlugosci osobnika, tj. 1 / dlugosc osobnika
     **/
    private Individual bestIndividual;
    private final double[] bestInGeneration;
    private final double[] averageInGeneration;
    private final double[] worstInGeneration;
    private Generation generation;

    public Simulation(int numberOfGenerations, int populationSize, Point[] genePool) {
        this.numberOfGenerations = numberOfGenerations;
        this.populationSize = populationSize;
        this.genePool = genePool;
        bestInGeneration = new double[numberOfGenerations];
        averageInGeneration = new double[numberOfGenerations];
        worstInGeneration = new double[numberOfGenerations];
    }

    public void simulate() throws FileNotFoundException {
        generation = new Generation(genePool, populationSize);
        bestIndividual = generation.getBestIndividualFromGeneration();

        for (int i = 0; i < numberOfGenerations; ++i) {
            bestInGeneration[i] = generation.getBestLengthInGeneration();
            averageInGeneration[i] = generation.getAverageLengthInGeneration();
            worstInGeneration[i] = generation.getWorstIndividualLengthInGeneration();
            if (generation.getBestLengthInGeneration() < bestIndividual.getLength()) {
                bestIndividual = generation.getBestIndividualFromGeneration();
            }
            System.out.format("Pokolenie: %d | najkrotszy: %.2f | srednio: %.2f | najdluzszy: %.2f\n",
                    (i+1), bestInGeneration[i], averageInGeneration[i], worstInGeneration[i]);
            generation.createNewGeneration();
        }
        SaveData.saveIndividual(bestIndividual, "najlepszyosobnik.dat");
        SaveData.saveCSVFile(bestInGeneration, averageInGeneration, worstInGeneration);
        System.out.println("\nEWOLUCJA ZAKONCZONA");
        System.out.println("\tNajlepszy utworzony osobnik ma dlugosc: " + bestIndividual.getLength());
    }
}
