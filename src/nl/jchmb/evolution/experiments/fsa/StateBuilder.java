package nl.jchmb.evolution.experiments.fsa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.jchmb.evolution.genotype.binary.Bit;
import nl.jchmb.evolution.phenotype.PhenotypeBuilder;
import nl.jchmb.evolution.phenotype.fsa.FSA;
import nl.jchmb.evolution.phenotype.fsa.State;
import nl.jchmb.evolution.phenotype.fsa.Transition;

public class StateBuilder implements PhenotypeBuilder<Bit, FSA<String>> {
	private int stateSize;
	private String[] alphabet;
	
	private Map<List<Bit>, State<String>> states;
	private State<String> startState = null;
	private Map<List<Bit>, Transition<String>> transitions;
	
	public StateBuilder(int stateSize, String[] alphabet) {
		this.stateSize = stateSize;
		this.alphabet = alphabet;
		reset();
	}
	
	private int getMaxAlphabetSize() {
		int i = 1;
		do {
			i *= 2;
		} while (i < alphabet.length);
		return i;
	}
	
	@Override
	public boolean matchesTag(List<Bit> buffer) {
		return true;
	}

	@Override
	public boolean isSatisfied(List<Bit> buffer) {
		return buffer.size() == stateSize * 2 + getMaxAlphabetSize();
	}
	
	private int getBitListValue(List<Bit> buffer) {
		int value = 0;
		int multiplier = 1;
		
		for (Bit bit : buffer) {
			value += multiplier * bit.getValue();
			multiplier *= 2;
		}
		
		return value;
	}
	
	private State<String> getState(List<Bit> buffer) {
		State<String> q;
		if (!states.containsKey(buffer)) {
			q = new State<String>();
			if (buffer.isEmpty()) {
				startState = q;
			}
			states.put(buffer, q);
		}
		return states.get(buffer);
	}

	@Override
	public void build(FSA<String> phenotype, List<Bit> buffer) {
		List<Bit> q1Buffer, q2Buffer;
		List<Bit> letterBuffer;
		State<String> q1, q2;
		String letter;
		
		q1Buffer = buffer.subList(0, stateSize);
		q2Buffer = buffer.subList(stateSize, stateSize + 2);
		letterBuffer = buffer.subList(stateSize * 2, buffer.size());
		
		q1 = getState(q1Buffer);
		q2 = getState(q2Buffer);
		letter = alphabet[getBitListValue(letterBuffer) % alphabet.length];
		
		q1.addTransition(new Transition<String>(q2, letter));
	}

	@Override
	public void reset() {
		states = new HashMap<List<Bit>, State<String>>();
		transitions = new HashMap<List<Bit>, Transition<String>>();
		startState = null;
	}

	@Override
	public void finish(FSA<String> phenotype) {
		if (!states.isEmpty()) {
			phenotype.setStartState(startState);
			phenotype.addGoalState(states.get(states.size() - 1));
		}
	}
}
