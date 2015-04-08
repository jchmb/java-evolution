package nl.jchmb.evolution.experiments.math;

import java.util.List;
import java.util.Random;

import nl.jchmb.evolution.core.Mutator;
import nl.jchmb.generic.generator.Generator;
import nl.jchmb.generic.sorter.RandomSorter;
import nl.jchmb.math.tree.BinaryOperator;
import nl.jchmb.math.tree.Connection;
import nl.jchmb.math.tree.Leaf;
import nl.jchmb.math.tree.Node;
import nl.jchmb.math.tree.Tree;

public class TreeMutator<T> implements Mutator<Tree<T>> {
	private float insertionProbability;
	private float mutationProbability;
	private Generator<BinaryOperator<String, T>> nodeGenerator;
	private Generator<Leaf<String, T>> leafGenerator;
	private Random random;
	
	public TreeMutator(
			float insertionProbability,
			float mutationProbability,
			Generator<BinaryOperator<String, T>> nodeGenerator,
			Generator<Leaf<String, T>> leafGenerator
	) {
		random = new Random();
		this.insertionProbability = insertionProbability;
		this.mutationProbability = mutationProbability;
		this.nodeGenerator = nodeGenerator;
		this.leafGenerator = leafGenerator;
	}
	
	@Override
	public Tree<T> mutate(Tree<T> genotype) {
		List<Connection<String, T>> connections = genotype.getNodes();
		new RandomSorter<Connection<String, T>>().sort(connections);
		
		if (random.nextFloat() <= mutationProbability && !connections.isEmpty()) {
			change(connections.remove(0));
		}
		
		if (random.nextFloat() <= insertionProbability && !connections.isEmpty()) {
			insert(connections.remove(0));
		}
		return genotype;
	}
	
	private void change(Connection<String, T> connection) {
		connection.setNode(leafGenerator.generate());
	}
	
	private void insert(Connection<String, T> connection) {
		Node<String, T> node = connection.getNode();
		connection.setNode(nodeGenerator.generate());
		((BinaryOperator<String, T>) connection.getNode()).setLeftChild(node);
	}
}