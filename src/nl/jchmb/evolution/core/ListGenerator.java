package nl.jchmb.evolution.core;

import java.util.ArrayList;
import java.util.List;

public class ListGenerator<T> implements Generator<List<T>> {
	private Generator<T> generator;
	private int populationSize;
	
	public ListGenerator(Generator<T> generator, int populationSize) {
		this.generator = generator;
		this.populationSize = populationSize;
	}
	
	@Override
	public List<T> generate() {
		List<T> genotypes = new ArrayList<T>();
		for (int i = 0; i < populationSize; i++) {
			genotypes.add(generator.generate());
		}
		return genotypes;
	}
}
