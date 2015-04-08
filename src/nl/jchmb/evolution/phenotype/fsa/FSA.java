package nl.jchmb.evolution.phenotype.fsa;

import java.util.ArrayList;
import java.util.Collection;

public class FSA<T> {
	private State<T> startState = null;
	private Collection<State<T>> goalStates;
	private State<T> currentState;
	
	public FSA() {
		goalStates = new ArrayList<State<T>>();
	}
	
	public void addGoalState(State<T> goalState) {
		goalStates.add(goalState);
	}
	
	public void reset() {
		currentState = startState;
	}
	
	public void setStartState(State<T> startState) {
		this.startState = startState;
		reset();
	}
	
	public State<T> getStartState() {
		return startState;
	}
	
	public State<T> getCurrentState() {
		return currentState;
	}
	
	public int countGoalStates() {
		return goalStates.size();
	}
	
	public boolean isGoalState() {
		return goalStates.contains(currentState);
	}
	
	public boolean isInvalidState() {
		return currentState == null;
	}
	
	public void walk(T key) {
		if (currentState != null) {
			currentState = currentState.walk(key);
		}
	}
	
	public static void main(String[] args) {
		State<String> q0, q1, q2;
		String[] testStrings = new String[]{
				"aa",
				"aba",
				"abbba",
				"abaaaa",
				"cc",
				"abbbbba"
		};
		
		q0 = new State<String>();
		q1 = new State<String>();
		q2 = new State<String>();
		
		q0.addTransition(q1, "a");
		q1.addTransition(q1, "b");
		q1.addTransition(q2, "a");
		
		FSA<String> fsa = new FSA<String>();
		fsa.setStartState(q0);
		fsa.addGoalState(q2);
		
		for (String testString : testStrings) {
			fsa.reset();
			for (int i = 0; i < testString.length(); i++) {
				fsa.walk(testString.substring(i, i + 1));
			}
			
			if (fsa.isGoalState()) {
				System.out.println(testString + " is in the language");
			} else {
				System.out.println(testString + " is not in the language");
			}
		}
	}
}
