package nl.jchmb.evolution.phenotype;

import java.util.List;

public interface PhenotypeBuilder<G, P> {
	public boolean matchesTag(List<G> buffer);
	public boolean isSatisfied(List<G> buffer);
	public void build(P phenotype, List<G> buffer);
	public void finish(P phenotype);
	public void reset();
}
