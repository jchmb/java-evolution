package nl.jchmb.evolution.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.jchmb.evolution.environment.Environment;
import nl.jchmb.evolution.operator.Operator;
import nl.jchmb.evolution.replicator.Replicator;

public class Population<G, P, R extends Replicator<G, P>> implements Iterable<R> {
	private List<R> replicators;
	private Environment<G, P, R> environment;
	
	public Population(List<R> replicators, Environment<G, P, R> environment) {
		this.replicators = replicators;
		this.environment = environment;
	}
	
	public Population<G, P, R> apply(Operator<G, P, R> operator) {
		return operator.apply(this);
	}

	@Override
	public Iterator<R> iterator() {
		return replicators.iterator();
	}
	
	public List<R> getReplicators() {
		return replicators;
	}
	
	public int getSize() {
		return replicators.size();
	}
	
	public Environment<G, P, R> getEnvironment() {
		return environment;
	}
}
