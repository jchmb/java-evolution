package nl.jchmb.evolution.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.jchmb.evolution.core.operator.Operator;

public class Population<G> implements Iterable<G> {
	private List<G> genotypes;
	
	public Population(List<G> genotypes) {
		this.genotypes = genotypes;
	}
	
	public Population() {
		this(new ArrayList<G>());
	}
	
	public Population<G> apply(Operator<G> operator) {
		return operator.apply(this);
	}
	
	public Population<G> copy() {
		return new Population<G>(new ArrayList<G>(genotypes));
	}

	@Override
	public Iterator<G> iterator() {
		return genotypes.iterator();
	}
	
	public List<G> getGenotypes() {
		return genotypes;
	}
	
	public int getSize() {
		return genotypes.size();
	}
}
