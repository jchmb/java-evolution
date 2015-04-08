package nl.jchmb.evolution.replicator;

import nl.jchmb.evolution.core.Expressor;

public class Replicator<G, P> {
	private G genotype;
	private Expressor<G, P> expressor;
	private P phenotype = null;
	
	public Replicator(G genotype, Expressor<G, P> expressor) {
		this.genotype = genotype;
		this.expressor = expressor;
		this.phenotype = null;
	}
	
	public void setGenotype(G genotype) {
		this.genotype = genotype;
	}
	
	public G getGenotype() {
		return genotype;
	}
	
	public final Expressor<G, P> getExpressor() {
		return expressor;
	}
	
	public P getPhenotype() {
		if (phenotype == null) {
			phenotype = expressor.express(genotype);
		}
		return phenotype;
	}
	
	public void resetPhenotype() {
		phenotype = null;
	}

}