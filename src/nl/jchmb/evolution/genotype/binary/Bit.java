package nl.jchmb.evolution.genotype.binary;

public enum Bit {
	ZERO, ONE;
	
	public int getValue() {
		switch (this) {
		case ZERO:
			return 0;
		case ONE:
			return 1;
		}
		return -1;
	}
}
