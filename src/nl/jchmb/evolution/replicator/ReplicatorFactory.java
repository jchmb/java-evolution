package nl.jchmb.evolution.replicator;

import java.util.ArrayList;
import java.util.List;

import nl.jchmb.evolution.core.Expressor;

public abstract class ReplicatorFactory<G, P, R extends Replicator<G, P>> {
	public List<R> produceReplicators(List<G> genotypes, Expressor<G, P> expressor) {
		List<R> replicators = new ArrayList<R>();
		for (G g : genotypes) {
			replicators.add(produceReplicator(g, expressor));
		}
		return replicators;
	}
	
	protected abstract R produceReplicator(G g, Expressor<G, P> expressor);
}
