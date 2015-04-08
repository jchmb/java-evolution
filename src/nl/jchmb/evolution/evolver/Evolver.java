package nl.jchmb.evolution.evolver;

import java.util.List;

import nl.jchmb.evolution.core.Expressor;
import nl.jchmb.evolution.environment.Environment;
import nl.jchmb.evolution.environment.FitnessCache;
import nl.jchmb.evolution.operator.Operator;
import nl.jchmb.evolution.replicator.Replicator;
import nl.jchmb.evolution.replicator.ReplicatorFactory;

public interface Evolver<G, P, R extends Replicator<G, P>> {
	public void addOperator(Operator<G, P, R> operator);
	public void doStep();
	public void evolve();
	public List<R> getReplicators();
	public List<G> getGenotypes();
	public void populate(List<G> genotypes, Expressor<G, P> expressor);
	public void setReplicatorFactory(ReplicatorFactory<G, P, R> replicatorFactory);
	public void setEnvironment(Environment<G, P, R> environment);
	public FitnessCache<G, P, R> getFitnessCache();
	public void setFitnessCache(FitnessCache<G, P, R> cache);
}