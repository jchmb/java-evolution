package nl.jchmb.evolution.genotype;

public class Codon {
	private Base[] bases;
	
	public Codon(Base[] bases) {
		this.bases = bases;
	}
	
	public Base[] getBases() {
		return bases;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Base base : bases) {
			builder.append(base.toString());
		}
		return builder.toString();
	}
	
	public boolean equals(Object other) {
		Codon otherCodon;
		Base[] otherBases;
		
		if (!(other instanceof Codon)) {
			return false;
		}
		otherCodon = (Codon) other;
		otherBases = otherCodon.getBases();
		if (bases.length != otherBases.length) {
			return false;
		}
		for (int i = 0; i < bases.length; i++) {
			if (bases[i] != otherBases[i]) {
				return false;
			}
		}
		return true;
	}
}
