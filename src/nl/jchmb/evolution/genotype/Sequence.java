package nl.jchmb.evolution.genotype;

import java.util.Arrays;
import java.util.List;

public class Sequence {
	private List<Codon> codons;
	
	public Sequence(List<Codon> codons) {
		this.codons = codons;
	}
	
	public Sequence(Codon[] codons) {
		this(Arrays.asList(codons));
	}
	
	public String toString() {
		int i = 0;
		StringBuilder builder = new StringBuilder();
		for (Codon codon : codons) {
			if (i++ > 0) {
				builder.append(" ");
			}
			builder.append(codon.toString());
		}
		return builder.toString();
	}
}
