package nl.jchmb.evolution.core;

public class IdentityExpressor<G> implements Expressor<G, G> {
	@Override
	public G express(G genotype) {
		return genotype;
	}
}
