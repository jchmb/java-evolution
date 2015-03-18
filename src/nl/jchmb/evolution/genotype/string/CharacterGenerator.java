package nl.jchmb.evolution.genotype.string;

import java.util.Random;

import nl.jchmb.evolution.core.Generator;

public class CharacterGenerator implements Generator<Character> {
	private Random random;
	private char[] characterSet;
	
	public CharacterGenerator(char[] characterSet) {
		random = new Random();
		this.characterSet = characterSet;
	}
	
	@Override
	public Character generate() {
		return characterSet[random.nextInt(characterSet.length)];
	}
}
