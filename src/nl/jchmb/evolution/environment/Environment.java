package nl.jchmb.evolution.environment;

import nl.jchmb.evolution.replicator.Replicator;

public interface Environment<G, P, R extends Replicator<G, P>> {
	public float evaluate(R replicator);
}
