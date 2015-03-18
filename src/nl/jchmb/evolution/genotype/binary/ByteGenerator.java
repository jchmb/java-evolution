package nl.jchmb.evolution.genotype.binary;

import java.util.Random;

import nl.jchmb.evolution.core.Generator;

public class ByteGenerator implements Generator<Integer> {
	private Random random;
	
	public ByteGenerator() {
		random = new Random();
	}
	
	@Override
	public Integer generate() {
		return random.nextInt(256);
	}
}
