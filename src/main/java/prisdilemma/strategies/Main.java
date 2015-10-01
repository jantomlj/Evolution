package prisdilemma.strategies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class Main {

	private final static Integer POPULATION_SIZE = 1000;
	private final static Integer INTERACTIONS_PER_GENERATION = 1000;
	private final static Integer NUMBER_OF_GENERATIONS = 10000;


	private static final Map<String, Integer> nameToFitnessMap = new HashMap<>();

	static {
		//nameToFitnessMap.put("Tit for tat", 1);
		nameToFitnessMap.put("Always cooperate", 1);
		nameToFitnessMap.put("Always defect", 1);
	}


	public static void main(String[] args) {

		List<Strategy> population = StrategyFactory.producePopulation(nameToFitnessMap, POPULATION_SIZE);
		for (int i = 0; i < NUMBER_OF_GENERATIONS; i++) {
			population = getNewGeneration(population);
			showResults(population);
		}
		showResults(population);
	}

	/**
	 * Show the percentage in which every strategy would be represented in the next generation.
	 */
	private static void showResults(List<Strategy> population) {
		System.out.println();
		double totalFitness = 0;
		for (Entry<String, Integer> entry : nameToFitnessMap.entrySet()) {
			totalFitness += entry.getValue();
		}
		for (Entry<String, Integer> entry : nameToFitnessMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + (double) entry.getValue() / totalFitness);
		}
	}


	/**
	 * Produce a new generation. Make every strategy of the population interact randomly with
	 * other strategies specified number of times. After all interactions calculate new fitness
	 * levels for each strategy and make new population accordingly.
	 */
	private static List<Strategy> getNewGeneration(List<Strategy> population) {
		Random rand = new Random();
		for (int i = 0; i < population.size(); i++) {
			for (int j = 0; j < INTERACTIONS_PER_GENERATION; j++) {
				Integer other;
				while (true) {
					other = Math.abs(Math.abs(rand.nextInt()) % population.size());
					if (!other.equals(i)) {
						break;
					}
				}
				Simulator.match(population.get(i), population.get(other));
			}
		}
		// reset fitness of each population to 0
		for (String name : nameToFitnessMap.keySet()) {
			nameToFitnessMap.put(name, 0);
		}

		// calculate new fitness values
		for (int i = 0; i < population.size(); i++) {
			String stName = population.get(i).getName();
			int fitnessBefore = nameToFitnessMap.get(stName);
			int newFitness = fitnessBefore + population.get(i).getFitness();
			nameToFitnessMap.put(stName, newFitness);
		}

		return StrategyFactory.producePopulation(nameToFitnessMap, POPULATION_SIZE);

	}

}
