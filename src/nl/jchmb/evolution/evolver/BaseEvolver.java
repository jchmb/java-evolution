package nl.jchmb.evolution.evolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import nl.jchmb.evolution.core.Expressor;
import nl.jchmb.evolution.core.Population;
import nl.jchmb.evolution.environment.Environment;
import nl.jchmb.evolution.environment.FitnessCache;
import nl.jchmb.evolution.operator.Operator;
import nl.jchmb.evolution.replicator.Replicator;
import nl.jchmb.evolution.replicator.ReplicatorFactory;

public abstract class BaseEvolver<G, P, R extends Replicator<G, P>> implements Evolver<G, P, R> {
	private volatile Population<G, P, R> population;
	private Environment<G, P, R> environment;
	private ReplicatorFactory<G, P, R> replicatorFactory;
	private List<Operator<G, P, R>> operators;
	private List<R> currentReplicators;
	private FitnessCache<G, P, R> cache;
	
	public BaseEvolver() {
		operators = new ArrayList<Operator<G, P, R>>();
	}
	
	@Override
	public void addOperator(Operator<G, P, R> operator) {
		operators.add(operator);
	}
	
	protected abstract boolean isDone();
	
	@Override
	public void evolve() {
		while (!isDone()) {
			doStep();
		}
	}
	
	@Override
	public void doStep() {
		currentReplicators = new Vector<R>(population.getReplicators());
		cache.reset();
		for (Operator<G, P, R> operator : operators) {
			population = operator.apply(population);
		}
	}
	
	@Override
	public void populate(List<G> genotypes, Expressor<G, P> expressor) {
		population = new Population<G, P, R>(replicatorFactory.produceReplicators(genotypes, expressor), environment);
	}
	
	@Override
	public List<G> getGenotypes() {
		List<G> genotypes = new ArrayList<G>();
		for (R replicator : population) {
			genotypes.add(replicator.getGenotype());
		}
		return genotypes;
	}
	
	@Override
	public void setReplicatorFactory(ReplicatorFactory<G, P, R> replicatorFactory) {
		this.replicatorFactory = replicatorFactory;
	}
	
	@Override
	public synchronized List<R> getReplicators() {
		return currentReplicators;
	}
	
	@Override
	public void setEnvironment(Environment<G, P, R> environment) {
		this.environment = environment;
	}
	
	@Override
	public void setFitnessCache(FitnessCache<G, P, R> cache) {
		this.cache = cache;
	}
	
	@Override
	public FitnessCache<G, P, R> getFitnessCache() {
		return cache;
	}
}
