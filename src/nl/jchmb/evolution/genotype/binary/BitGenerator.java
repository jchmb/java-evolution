package nl.jchmb.evolution.genotype.binary;

import java.util.Random;

import nl.jchmb.generic.generator.Generator;

public class BitGenerator implements Generator<Bit> {
	private Random random;
	
	public BitGenerator() {
		random = new Random();
	}
	
	@Override
	public Bit generate() {
		int r = random.nextInt(2);
		return r == 1 ? Bit.ONE : Bit.ZERO;
	}
}
