package nl.jchmb.evolution.environment;

import nl.jchmb.evolution.replicator.Replicator;
import nl.jchmb.generic.sorter.ComparatorSorter;

public class FitnessSorter<G, P, R extends Replicator<G, P>> extends ComparatorSorter<R> {
	public FitnessSorter(FitnessCache<G, P, R> cache) {
		super(new FitnessComparator<G, P, R>(cache));
	}
}
