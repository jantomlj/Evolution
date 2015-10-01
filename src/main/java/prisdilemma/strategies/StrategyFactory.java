package prisdilemma.strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StrategyFactory {

	/**
	 * Function that returns a new strategy based on the name. Strategy will have given id.
	 */
	public static Strategy makeStrategy(String name, Integer id) {

		switch (name) {
		case "Tit for tat":
			return new Strategies.TitForTat(id);

		case "Always defect":
			return new Strategies.AlwaysDefect(id);

		case "Always cooperate":
			return new Strategies.AlwaysCooperate(id);

		default:
			throw new IllegalArgumentException("Strategy name is not found");
		}
	}

	/**
	 * nameToFitnessMap is a map that connects every strategy name to the total fitness of all
	 * strategies with that name in the former population. Based on the percentage of fitness
	 * each strategy has compared to total amount of fitness in the population, new population is made,
	 * in which the number of specified strategies compared to the population size is the same
	 * as the above percentage.
	 */
	public static List<Strategy> producePopulation(Map<String, Integer> nameToFitnessMap, Integer populationSize) {
		int totalFitness = 0;
		// works well with non-negative fitness levels
		for (Entry<String, Integer> entry : nameToFitnessMap.entrySet()) {
			totalFitness += entry.getValue();
		}
		
		Integer id = 1;
		List<Strategy> population = new ArrayList<>();
		for (Entry<String, Integer> entry : nameToFitnessMap.entrySet()) {
			double percentage = (double) entry.getValue() / (double) totalFitness;
			int numberOfUnits = (int) (percentage * (double) populationSize);
			for (int i = 0; i < numberOfUnits; i++) {
				population.add(makeStrategy(entry.getKey(), id++));
			}
		}
		return population;
	}
}
