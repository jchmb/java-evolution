package nl.jchmb.utils;

import java.util.ArrayList;
import java.util.Collection;

import nl.jchmb.evolution.core.Generator;

public class AggregateGenerator<T> implements Generator<T> {
	private Collection<Generator<T>> generators;
	
	public AggregateGenerator() {
		generators = new ArrayList<Generator<T>>();
	}

	@Override
	public T generate() {
		
	}
}
