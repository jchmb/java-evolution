package nl.jchmb.evolution.experiments.string;

import java.util.ArrayList;
import java.util.List;

import nl.jchmb.utils.Cloner;

public class SentenceCloner implements Cloner<List<Character>> {
	@Override
	public List<Character> clone(List<Character> object) {
		return new ArrayList<Character>(object);
	}
}
