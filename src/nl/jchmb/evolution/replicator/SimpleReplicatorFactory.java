package nl.jchmb.evolution.replicator;

import nl.jchmb.evolution.core.Expressor;

public class SimpleReplicatorFactory<G, P> extends ReplicatorFactory<G, P, Replicator<G, P>> {
	@Override
	protected Replicator<G, P> produceReplicator(G g, Expressor<G, P> expressor) {
		return new Replicator<G, P>(g, expressor);
	}

}
