package nl.jchmb.evolution.core.operator;

import java.util.ArrayList;
import java.util.List;

import nl.jchmb.evolution.core.Population;
import nl.jchmb.utils.Cloner;

public class CloneOperator<G> implements Operator<G> {
	private int numClones;
	private Cloner<G> cloner;
	
	public CloneOperator(Cloner<G> cloner, int numClones) {
		this.cloner = cloner;
		this.numClones = numClones;
	}
	
	@Override
	public Population<G> apply(Population<G> population) {
		List<G> genotypes = new ArrayList<G>();
		for (G genotype : population) {
			genotypes.add(genotype);
			for (int i = 0; i < numClones; i++) {
				genotypes.add(cloner.clone(genotype));
			}
		}
		return new Population<G>(genotypes);
	}
}
