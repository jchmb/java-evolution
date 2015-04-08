package nl.jchmb.evolution.repository;

import nl.jchmb.evolution.evolver.Evolver;
import nl.jchmb.evolution.evolver.SimpleEvolver;
import nl.jchmb.evolution.replicator.PlanetReplicator;
import nl.jchmb.evolution.replicator.PlanetReplicatorFactory;
import nl.jchmb.math.random.FloatGenerator;
import nl.jchmb.math.vector.FloatVectorGenerator;

public class EvolverRepository<G, P> extends Repository<Evolver<G, P, PlanetReplicator<G, P>>> {
	public EvolverRepository() {
		addPlanetEvolver();
	}
	
	private void addPlanetEvolver() {
		Evolver<G, P, PlanetReplicator<G, P>> evolver = new SimpleEvolver<G, P, PlanetReplicator<G, P>>();
		evolver.setReplicatorFactory(
				new PlanetReplicatorFactory<G, P>(
						new FloatVectorGenerator(
								2,
								new FloatGenerator()
						)
				)
		);
		add(
				new SimpleSource<Evolver<G, P, PlanetReplicator<G, P>>>(evolver, "PlanetEvolver")
		);
	}
}