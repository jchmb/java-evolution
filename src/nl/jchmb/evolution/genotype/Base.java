package nl.jchmb.evolution.genotype;

import java.util.Random;

public enum Base {
	A, C, G, T;
	
	public static Base getRandomBase() {
		return Base.values()[Math.abs(new Random().nextInt()) % 4];
	}
}
