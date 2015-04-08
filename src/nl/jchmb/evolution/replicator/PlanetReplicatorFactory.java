package nl.jchmb.evolution.replicator;

import nl.jchmb.evolution.core.Expressor;
import nl.jchmb.generic.generator.Generator;
import nl.jchmb.math.vector.Vector;

public class PlanetReplicatorFactory<G, P> extends ReplicatorFactory<G, P, PlanetReplicator<G, P>> {
	private Generator<Vector<Float>> generator;
	
	public PlanetReplicatorFactory(Generator<Vector<Float>> generator) {
		this.generator = generator;
	}

	@Override
	protected PlanetReplicator<G, P> produceReplicator(G g,
			Expressor<G, P> expressor) {
		PlanetReplicator<G, P> replicator = new PlanetReplicator<G, P>(g, expressor);
		replicator.setPosition(generator.generate());
		return replicator;
	}
}
