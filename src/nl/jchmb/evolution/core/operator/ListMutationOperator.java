package nl.jchmb.evolution.core.operator;

import java.util.List;
import java.util.Random;

import nl.jchmb.evolution.core.Generator;
import nl.jchmb.evolution.core.Population;

public class ListMutationOperator<E> implements Operator<List<E>> {
	private Random random;
	private float expansionRatio;
	private Generator<E> generator;
	
	public ListMutationOperator(Generator<E> generator, float expansionRatio) {
		random = new Random();
		this.generator = generator;
		this.expansionRatio = expansionRatio;
	}
	
	public ListMutationOperator(Generator<E> generator) {
		this(generator, 0.5f);
	}
	
	@Override
	public Population<List<E>> apply(Population<List<E>> population) {
		for (List<E> list : population) {
			mutate(list);
		}
		return population;
	}
	
	private void mutate(List<E> list) {
		int randomOffset = Math.abs(random.nextInt()) % list.size();
		E newElement;
		if (random.nextDouble() >= expansionRatio) {
			newElement = generator.generate();
			list.add(randomOffset, newElement);
		} else {
			list.remove(randomOffset);
		}
	}
}
