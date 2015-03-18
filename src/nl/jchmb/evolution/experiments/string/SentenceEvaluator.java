package nl.jchmb.evolution.experiments.string;

import nl.jchmb.evolution.core.Evaluator;
import nl.jchmb.utils.LevenshteinDistance;

public class SentenceEvaluator implements Evaluator<String> {
	private String stringToMatch;
	
	public SentenceEvaluator(String stringToMatch) {
		this.stringToMatch = stringToMatch;
	}
	
	@Override
	public float evaluate(String phenotype) {
		float distance = LevenshteinDistance.computeLevenshteinDistance(stringToMatch, phenotype);
		return distance;
	}
}
