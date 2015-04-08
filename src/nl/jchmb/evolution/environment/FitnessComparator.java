package nl.jchmb.evolution.environment;

import java.util.Comparator;

import nl.jchmb.evolution.replicator.Replicator;

public class FitnessComparator<G, P, R extends Replicator<G, P>> implements Comparator<R> {
	private FitnessCache<G, P, R> cache;
	
	public FitnessComparator(FitnessCache<G, P, R> cache) {
		this.cache = cache;
	}

	@Override
	public int compare(R arg0, R arg1) {
		float f1 = cache.getFitness(arg0), f2 = cache.getFitness(arg1);
		
		if (Float.isNaN(f1) && Float.isNaN(f2)) {
			return 0;
		}
		if (Float.isNaN(f1)) {
			return 1;
		}
		if (Float.isNaN(f2)) {
			return -1;
		}
		
		return f1 == f2 ? 0 : (f1 > f2 ? 1 : -1);
	}
}
