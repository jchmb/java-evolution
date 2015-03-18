package nl.jchmb.evolution.core;

import java.util.ArrayList;
import java.util.List;

import nl.jchmb.evolution.core.operator.Operator;

public class Evolver<G, P> {
	private static final int MAX_GENERATIONS = 250999;
	
	private List<Operator<G>> operators;
	private Population<G> population;
	private int generation = 0;
	
	protected Evolver(Population<G> initialPopulation) {
		operators = new ArrayList<Operator<G>>();
		population = initialPopulation;
	}
	
	public void addOperator(Operator<G> operator) {
		operators.add(operator);
	}
	
	private void doStep() {
		Population<G> newPopulation = population.copy();
		for (Operator<G> operator : operators) {
			newPopulation = newPopulation.apply(operator);
		}
		population = newPopulation;
		generation++;
	}
	
	public Population<G> getPopulation() {
		return population;
	}
	
	protected boolean isDone() {
		return generation >= MAX_GENERATIONS;
	}
	
	public void evolve() {
		while (!isDone()) {
			doStep();
		}
	}
}
