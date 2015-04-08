package nl.jchmb.evolution.operator;

import nl.jchmb.evolution.core.Population;
import nl.jchmb.evolution.replicator.Replicator;

public interface Operator<G, P, R extends Replicator<G, P>> {
	public Population<G, P, R> apply(Population<G, P, R> population);
}
