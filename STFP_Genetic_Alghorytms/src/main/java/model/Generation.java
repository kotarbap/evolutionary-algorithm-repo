package model;

import data.GenerateData;

import java.util.Arrays;
import java.util.Random;

public class Generation {
    private Individual[] individuals;
    private final Random random = new Random();

    public Generation(Point[] points, int populationSize) {
        generateFirstPopulation(points ,populationSize);
    }

    public void createNewGeneration() {
        Individual[] newPopulation = new Individual[individuals.length];

        for (int i = 0; i < individuals.length / 2; ++i) {
            if (i == 0) {
                newPopulation[0] = individuals[0].createDescendant();
                newPopulation[1] = individuals[0].createDescendant();
            } else {
                int randomIndividual = random.nextInt(0, individuals.length);
                newPopulation[2*i] = individuals[randomIndividual].createDescendant();
                newPopulation[2*i + 1] = individuals[randomIndividual].createDescendant();
            }
        }
        Arrays.sort(newPopulation);
        individuals = newPopulation;
    }

    public Individual getBestIndividualFromGeneration() {
        return individuals[0];
    }

    public double getWorstIndividualLengthInGeneration() {
        return individuals[individuals.length - 1].getLength();
    }

    public double getAverageLengthInGeneration() {
        double result = 0;
        for (Individual individual: individuals) {
            result += individual.getLength();
        }
        return result / individuals.length;
    }

    public double getBestLengthInGeneration() {
        return individuals[0].getLength();
    }

    private void generateFirstPopulation(Point[] points, int populationSize) {
        individuals = new Individual[populationSize];
        Random random = new Random();
        for (int i = 0; i < populationSize; ++i) {
            GenerateData.randomSwapPoints(points, random);
            individuals[i] = new Individual(points);
        }
        Arrays.sort(individuals);
    }
}
