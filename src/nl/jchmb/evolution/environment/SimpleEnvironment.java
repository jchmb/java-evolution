package nl.jchmb.evolution.environment;

import nl.jchmb.evolution.core.Evaluator;
import nl.jchmb.evolution.replicator.Replicator;

public class SimpleEnvironment<G, P, R extends Replicator<G, P>> implements Environment<G, P, R> {
	private Evaluator<P> evaluator;
	
	public SimpleEnvironment(Evaluator<P> evaluator) {
		this.evaluator = evaluator;
	}
	
	@Override
	public float evaluate(R replicator) {
		return evaluator.evaluate(replicator.getPhenotype());
	}
}
