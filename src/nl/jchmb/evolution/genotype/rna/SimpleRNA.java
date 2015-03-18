package nl.jchmb.evolution.genotype.rna;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.jchmb.evolution.genotype.Base;
import nl.jchmb.evolution.genotype.Codon;

public class SimpleRNA implements Iterable<Base> {
	private List<Base> chromosome;
	
	public SimpleRNA(List<Base> chromosome) {
		this.chromosome = chromosome;
	}
	
	public int getSize() {
		return chromosome.size();
	}
	
	public void insert(int offset, Base base) {
		chromosome.add(offset, base);
	}
	
	public void remove(int offset) {
		chromosome.remove(offset);
	}

	@Override
	public Iterator<Base> iterator() {
		return chromosome.iterator();
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Base base : chromosome) {
			builder.append(base.toString());
		}
		return builder.toString();
	}
	
	public static SimpleRNA getRandomSample(int size) {
		List<Base> chromosome = new ArrayList<Base>(size);
		for (int i = 0; i < size; i++) {
			chromosome.add(Base.getRandomBase());
		}
		return new SimpleRNA(chromosome);
	}
}
