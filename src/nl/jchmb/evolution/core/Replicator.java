package nl.jchmb.evolution.core;

public class Replicator<G, P> {
	private G genotype;
	private Expressor<G, P> expressor;
	
	public Replicator(G genotype, Expressor<G, P> expressor) {
		this.genotype = genotype;
		this.expressor = expressor;
	}
	
	public G getGenotype() {
		return genotype;
	}
	
	public P getPhenotype() {
		return expressor.express(genotype);
	}
}
