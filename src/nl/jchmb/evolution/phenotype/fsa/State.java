package nl.jchmb.evolution.phenotype.fsa;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class State<T> implements Iterable<Transition<T>> {
	private Set<Transition<T>> transitions;
	
	public State() {
		transitions = new HashSet<Transition<T>>();
	}
	
	public void addTransition(Transition<T> transition) {
		transitions.add(transition);
	}
	
	public void addTransition(State<T> newState, T identifier) {
		addTransition(new Transition<T>(newState, identifier));
	}
	
	public State<T> walk(T key) {
		State<T> nextState;
		for (Transition<T> transition : transitions) {
			nextState = transition.transition(key);
			if (nextState != null) {
				return nextState;
			}
		}
		return null;
	}

	@Override
	public Iterator<Transition<T>> iterator() {
		return transitions.iterator();
	}
}
