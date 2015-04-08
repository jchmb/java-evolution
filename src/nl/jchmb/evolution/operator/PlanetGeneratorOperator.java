package nl.jchmb.evolution.operator;

import java.util.ArrayList;
import java.util.List;

import nl.jchmb.evolution.core.Expressor;
import nl.jchmb.evolution.core.ListGenerator;
import nl.jchmb.evolution.core.Population;
import nl.jchmb.evolution.replicator.PlanetReplicator;
import nl.jchmb.evolution.replicator.PlanetReplicatorFactory;
import nl.jchmb.generic.generator.Generator;

public class PlanetGeneratorOperator<G, P> implements Operator<G, P, PlanetReplicator<G, P>> {
	private Generator<List<G>> listGenerator;
	private PlanetReplicatorFactory<G, P> factory;
	
	public PlanetGeneratorOperator(
			Generator<G> genotypeGenerator,
			PlanetReplicatorFactory<G, P> factory,
			int count
	) {
		listGenerator = new ListGenerator<G>(genotypeGenerator, count);
		this.factory = factory;
	}
	
	@Override
	public Population<G, P, PlanetReplicator<G, P>> apply(
			Population<G, P, PlanetReplicator<G, P>> population) {
		List<PlanetReplicator<G, P>> replicators = new ArrayList<PlanetReplicator<G, P>>(population.getReplicators());
		Expressor<G, P> expressor = replicators.get(0).getExpressor();
		List<G> genotypes = listGenerator.generate();
		replicators.addAll(factory.produceReplicators(genotypes, expressor));
		return new Population<G, P, PlanetReplicator<G, P>>(
				replicators,
				population.getEnvironment()
		);
	}
}
