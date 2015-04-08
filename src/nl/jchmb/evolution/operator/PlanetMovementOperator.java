package nl.jchmb.evolution.operator;

import nl.jchmb.evolution.core.Population;
import nl.jchmb.evolution.replicator.PlanetReplicator;
import nl.jchmb.generic.generator.Generator;
import nl.jchmb.math.vector.Vector;

public class PlanetMovementOperator<G, P> implements Operator<G, P, PlanetReplicator<G, P>> {
	private Generator<Vector<Float>> generator;
	
	public PlanetMovementOperator(Generator<Vector<Float>> generator) {
		this.generator = generator;
	}
	
	@Override
	public Population<G, P, PlanetReplicator<G, P>> apply(
			Population<G, P, PlanetReplicator<G, P>> population) {
		for (PlanetReplicator<G, P> replicator : population) {
			move(replicator);
		}
		return population;
	}
	
	private void move(PlanetReplicator<G, P> replicator) {
		replicator.walk(generator.generate());
	}
}
