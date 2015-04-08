package nl.jchmb.evolution.experiments.math;

import java.util.Random;

import nl.jchmb.generic.generator.Generator;
import nl.jchmb.math.tree.Constant;
import nl.jchmb.math.tree.Leaf;
import nl.jchmb.math.tree.Variable;

public class LeafGenerator<K, T> implements Generator<Leaf<K, T>> {
	private float variableProbability;
	private Random random;
	private K[] identifiers;
	private Generator<T> constantGenerator;
	
	public LeafGenerator(K[] identifiers, float variableProbability, Generator<T> constantGenerator) {
		this.identifiers = identifiers;
		this.variableProbability = variableProbability;
		this.constantGenerator = constantGenerator;
		random = new Random();
	}
	
	@Override
	public Leaf<K, T> generate() {
		if (random.nextFloat() <= variableProbability) {
			return new Variable<K, T>(identifiers[random.nextInt(identifiers.length)]);
		} else {
			return new Constant<K, T>(constantGenerator.generate());
		}
	}

}
