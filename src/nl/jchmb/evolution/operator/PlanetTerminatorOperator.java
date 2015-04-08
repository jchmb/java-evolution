package nl.jchmb.evolution.operator;

import java.util.ArrayList;
import java.util.List;

import nl.jchmb.evolution.core.Population;
import nl.jchmb.evolution.core.Selection;
import nl.jchmb.evolution.environment.Environment;
import nl.jchmb.evolution.environment.FitnessCache;
import nl.jchmb.evolution.environment.FitnessSorter;
import nl.jchmb.evolution.replicator.PlanetReplicator;
import nl.jchmb.generic.sorter.ReverseSorter;

public class PlanetTerminatorOperator<G, P> implements Operator<G, P, PlanetReplicator<G, P>> {
	private FitnessCache<G, P, PlanetReplicator<G, P>> cache;
	private Environment<G, P, PlanetReplicator<G, P>> environment;
	private Selection<G, P, PlanetReplicator<G, P>> selection;
	
	public PlanetTerminatorOperator(
			FitnessCache<G, P, PlanetReplicator<G, P>> cache,
			Environment<G, P, PlanetReplicator<G, P>> environment,
			Selection<G, P, PlanetReplicator<G, P>> selection
	) {
		this.cache = cache;
		this.environment = environment;
		this.selection = selection;
	}
	
	@Override
	public Population<G, P, PlanetReplicator<G, P>> apply(
			Population<G, P, PlanetReplicator<G, P>> population) {
		List<PlanetReplicator<G, P>> replicators = new ArrayList<PlanetReplicator<G, P>>(population.getReplicators());
		Population<G, P, PlanetReplicator<G, P>> newPopulation = new Population<G, P, PlanetReplicator<G, P>>(
				replicators,
				environment
		);
		List<PlanetReplicator<G, P>> selectedReplicators;
		new FitnessSorter<G, P, PlanetReplicator<G, P>>(cache).sort(replicators);
		new ReverseSorter<PlanetReplicator<G, P>>().sort(replicators);
		selectedReplicators = selection.select(replicators);
		replicators.removeAll(selectedReplicators);
		return new Population<G, P, PlanetReplicator<G, P>>(
				replicators,
				environment
		);
	}
}
