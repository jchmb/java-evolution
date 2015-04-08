package nl.jchmb.evolution.core;

import nl.jchmb.generic.generator.Generator;

public abstract class PrimalSoup<G, P> implements Generator<G> {
	private Generator<G> generator;
	private Expressor<G, P> expressor;
	private Evaluator<P> evaluator;
}
