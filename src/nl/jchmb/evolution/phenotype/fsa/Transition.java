package nl.jchmb.evolution.phenotype.fsa;

public class Transition<T> {
	private State<T> newState;
	private T identifier;
	
	public Transition(State<T> newState, T identifier) {
		this.newState = newState;
		this.identifier = identifier;
	}
	
	public State<T> transition(T key) {
		if (identifier.equals(key)) {
			return newState;
		}
		return null;
	}
}
