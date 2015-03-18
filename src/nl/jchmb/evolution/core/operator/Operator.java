package nl.jchmb.evolution.core.operator;

import nl.jchmb.evolution.core.Population;


public interface Operator<G> {
	public Population<G> apply(Population<G> population);
}
