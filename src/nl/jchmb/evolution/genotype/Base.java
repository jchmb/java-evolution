package nl.jchmb.evolution.genotype;

import java.util.Random;

public enum Base {
	A, C, G, T;
	
	public static Base getRandomBase() {
		return Base.values()[Math.abs(new Random().nextInt()) % 4];
	}
	
	public int getRank() {
		switch (this) {
			case A:
				return 0;
			case C:
				return 1;
			case G:
				return 2;
			case T:
				return 3;
			default:
				return -1;
		}
	}
}
