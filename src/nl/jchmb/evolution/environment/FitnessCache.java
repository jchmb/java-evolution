package nl.jchmb.evolution.environment;

import java.util.HashMap;
import java.util.Map;

import nl.jchmb.evolution.replicator.Replicator;

public class FitnessCache<G, P, R extends Replicator<G, P>> {
	private Map<R, Float> fitnessScores;
	private Environment<G, P, R> environment;
	private R bestReplicator = null;
	
	public FitnessCache(Environment<G, P, R> environment) {
		this.environment = environment;
		fitnessScores = new HashMap<R, Float>();
	}
	
	public void reset() {
		bestReplicator = null;
		fitnessScores.clear();
	}
	
	public R getBestReplicator() {
		return bestReplicator;
	}
	
	public float getFitness(R replicator) {
		float fitness;
		if (!fitnessScores.containsKey(replicator)) {
			fitnessScores.put(replicator, environment.evaluate(replicator));
		}
		fitness = fitnessScores.get(replicator).floatValue();
		if (bestReplicator == null || (replicator != bestReplicator && fitness < getFitness(bestReplicator))) {
			bestReplicator = replicator;
		}
		return fitness;
	}
}
