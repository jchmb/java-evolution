package nl.jchmb.evolution.replicator;

import nl.jchmb.generic.cloner.Cloner;

public class PlanetReplicatorCloner<G, P> implements Cloner<PlanetReplicator<G, P>> {
	private Cloner<G> genotypeCloner;
	
	public PlanetReplicatorCloner(Cloner<G> genotypeCloner) {
		this.genotypeCloner = genotypeCloner;
	}
	
	@Override
	public PlanetReplicator<G, P> clone(PlanetReplicator<G, P> object) {
		PlanetReplicator<G, P> replicator = new PlanetReplicator<G, P>(
				genotypeCloner.clone(object.getGenotype()),
				object.getExpressor()
		);
		replicator.setPosition(object.getPosition());
		return replicator;
	}
}
