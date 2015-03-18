package nl.jchmb.evolution.experiments.string;

import java.util.List;

import nl.jchmb.evolution.core.Evolver;
import nl.jchmb.evolution.core.ListGenerator;
import nl.jchmb.evolution.core.Population;
import nl.jchmb.evolution.core.PopulationGenerator;
import nl.jchmb.evolution.core.operator.CloneOperator;
import nl.jchmb.evolution.core.operator.ListMutationOperator;
import nl.jchmb.evolution.core.operator.SelectionOperator;
import nl.jchmb.evolution.genotype.string.AlphabetGenerator;

public class SentenceEvolver extends Evolver<List<Character>, String> {
	private static final String STRING_TO_MATCH = "matchthisstring";
	
	public SentenceEvolver(int populationSize) {
		super(
				new PopulationGenerator<List<Character>>(
						new ListGenerator<Character>(
								new AlphabetGenerator(),
								populationSize
						),
						populationSize
				).generate()
		);
		addOperator(
				new CloneOperator<List<Character>>(
						new SentenceCloner(),
						4
				)
		);
		addOperator(
				new ListMutationOperator<Character>(new AlphabetGenerator())
		);
		addOperator(
				new SelectionOperator<List<Character>, String>(
						0.25f,
						new SentenceExpressor(),
						new SentenceEvaluator(STRING_TO_MATCH)
				)
		);
	}
	
	public static void main(String[] args) {
		Population<List<Character>> population;
		SentenceEvolver evolver = new SentenceEvolver(25);
		evolver.evolve();
		population = evolver.getPopulation();
		for (List<Character> sentence : population) {
			System.out.println(new SentenceExpressor().express(sentence));
		}
	}
}
