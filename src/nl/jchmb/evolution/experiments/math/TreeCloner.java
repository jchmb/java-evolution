package nl.jchmb.evolution.experiments.math;

import nl.jchmb.generic.cloner.Cloner;
import nl.jchmb.math.tree.Tree;

public class TreeCloner<T> implements Cloner<Tree<T>> {
	@Override
	public Tree<T> clone(Tree<T> object) {
		return object.copy();
	}
}
