package nl.jchmb.evolution.experiments.math;

import nl.jchmb.generic.generator.Generator;
import nl.jchmb.math.tree.Tree;

public class TreeGenerator<T> implements Generator<Tree<T>> {
	private BinaryOperatorGenerator<T> generator;
	
	public TreeGenerator(BinaryOperatorGenerator<T> generator) {
		this.generator = generator;
	}
	
	@Override
	public Tree<T> generate() {
		return new Tree<T>(generator.generate());
	}

}
