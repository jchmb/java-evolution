package nl.jchmb.evolution.operator;

import java.util.ArrayList;
import java.util.List;

import nl.jchmb.evolution.core.Mutator;
import nl.jchmb.evolution.core.Population;
import nl.jchmb.evolution.core.Selection;
import nl.jchmb.evolution.environment.Environment;
import nl.jchmb.evolution.environment.FitnessCache;
import nl.jchmb.evolution.environment.FitnessSorter;
import nl.jchmb.evolution.replicator.PlanetReplicator;
import nl.jchmb.evolution.replicator.PlanetReplicatorCloner;
import nl.jchmb.generic.cloner.Cloner;

public class PlanetCloneOperator<G, P> implements Operator<G, P, PlanetReplicator<G, P>> {
	private Cloner<PlanetReplicator<G, P>> cloner;
	private FitnessCache<G, P, PlanetReplicator<G, P>> cache;
	private Mutator<G> mutator;
	private Selection<G, P, PlanetReplicator<G, P>> selection;
	private Environment<G, P, PlanetReplicator<G, P>> environment;
	
	public PlanetCloneOperator(
			Cloner<G> genotypeCloner,
			Mutator<G> mutator,
			Selection<G, P, PlanetReplicator<G, P>> selection,
			FitnessCache<G, P, PlanetReplicator<G, P>> cache,
			Environment<G, P, PlanetReplicator<G, P>> environment
	) {
		cloner = new PlanetReplicatorCloner<G, P>(genotypeCloner);
		this.cache = cache;
		this.mutator = mutator;
		this.environment = environment;
		this.selection = selection;
	}
	
	@Override
	public Population<G, P, PlanetReplicator<G, P>> apply(Population<G, P, PlanetReplicator<G, P>> population) {
		List<PlanetReplicator<G, P>> replicators = new ArrayList<PlanetReplicator<G, P>>(population.getReplicators());
		Population<G, P, PlanetReplicator<G, P>> newPopulation = new Population<G, P, PlanetReplicator<G, P>>(
				replicators,
				environment
		);
		List<PlanetReplicator<G, P>> selectedReplicators;
		PlanetReplicator<G, P> clonedReplicator;
		new FitnessSorter<G, P, PlanetReplicator<G, P>>(cache).sort(replicators);
		selectedReplicators = selection.select(replicators);
		for (PlanetReplicator<G, P> replicator : selectedReplicators) {
			clonedReplicator = cloner.clone(replicator);
			clonedReplicator.setGenotype(mutator.mutate(clonedReplicator.getGenotype()));
			replicators.add(clonedReplicator);
		}
		return newPopulation;
	}
}
