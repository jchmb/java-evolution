package nl.jchmb.evolution.experiments.string;

import java.util.List;

import nl.jchmb.evolution.core.Expressor;

public class SentenceExpressor implements Expressor<List<Character>, String> {
	@Override
	public String express(List<Character> genotype) {
		StringBuilder builder = new StringBuilder();
		for (Character character : genotype) {
			builder.append(character);
		}
		return builder.toString();
	}
}
