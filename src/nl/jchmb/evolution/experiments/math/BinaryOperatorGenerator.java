package nl.jchmb.evolution.experiments.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nl.jchmb.generic.generator.Generator;
import nl.jchmb.math.function.BinaryFunction;
import nl.jchmb.math.tree.BinaryOperator;
import nl.jchmb.math.tree.Leaf;
import nl.jchmb.math.tree.Node;

public class BinaryOperatorGenerator<T> implements Generator<BinaryOperator<String, T>> {
	private List<BinaryFunction<T, T, T>> availableFunctions;
	private Generator<Leaf<String, T>> leafGenerator;
	private float branchingProbability;
	private Random random;
	
	public BinaryOperatorGenerator(
			Generator<Leaf<String, T>> leafGenerator,
			float branchingProbability
	) {
		random = new Random();
		availableFunctions = new ArrayList<BinaryFunction<T, T, T>>();
		this.leafGenerator = leafGenerator;
		this.branchingProbability = branchingProbability;
	}
	
	public BinaryOperatorGenerator(
			Generator<Leaf<String, T>> leafGenerator
	) {
		this(leafGenerator, 0.0f);
	}
	
	public BinaryOperatorGenerator<T> add(BinaryFunction<T, T, T> f) {
		availableFunctions.add(f);
		return this;
	}
	
	private Node<String, T> generateNode() {
		if (branchingProbability != 0.0f && random.nextFloat() <= branchingProbability) {
			return generate();
		} else {
			return leafGenerator.generate();
		}
	}
	
	@Override
	public BinaryOperator<String, T> generate() {
		BinaryFunction<T, T, T> f = availableFunctions.get(random.nextInt(availableFunctions.size()));
		Node<String, T> n1, n2;
		n1 = generateNode();
		n2 = generateNode();
		return new BinaryOperator<String, T>(
				f, n1, n2
		);
	}
}
