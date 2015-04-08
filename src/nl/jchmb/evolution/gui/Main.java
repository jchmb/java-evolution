package nl.jchmb.evolution.gui;

import java.awt.Dimension;
import java.util.List;

import nl.jchmb.evolution.core.IdentityExpressor;
import nl.jchmb.evolution.core.ListGenerator;
import nl.jchmb.evolution.core.Selection;
import nl.jchmb.evolution.environment.Environment;
import nl.jchmb.evolution.environment.FitnessCache;
import nl.jchmb.evolution.environment.SimpleEnvironment;
import nl.jchmb.evolution.evolver.Evolver;
import nl.jchmb.evolution.experiments.math.BinaryOperatorGenerator;
import nl.jchmb.evolution.experiments.math.LeafGenerator;
import nl.jchmb.evolution.experiments.math.LinearFunctionEvaluator;
import nl.jchmb.evolution.experiments.math.PolynomialFunctionEvaluator;
import nl.jchmb.evolution.experiments.math.TreeCloner;
import nl.jchmb.evolution.experiments.math.TreeGenerator;
import nl.jchmb.evolution.experiments.math.TreeMutator;
import nl.jchmb.evolution.operator.PlanetCloneOperator;
import nl.jchmb.evolution.operator.PlanetGeneratorOperator;
import nl.jchmb.evolution.operator.PlanetMovementOperator;
import nl.jchmb.evolution.operator.PlanetTerminatorOperator;
import nl.jchmb.evolution.replicator.PlanetReplicator;
import nl.jchmb.evolution.replicator.PlanetReplicatorFactory;
import nl.jchmb.evolution.repository.EvolverRepository;
import nl.jchmb.generic.generator.Generator;
import nl.jchmb.generic.generator.SimpleGenerator;
import nl.jchmb.math.arithmetic.FloatAddition;
import nl.jchmb.math.arithmetic.FloatDivision;
import nl.jchmb.math.arithmetic.FloatMultiplication;
import nl.jchmb.math.function.BinaryFunction;
import nl.jchmb.math.random.FloatGenerator;
import nl.jchmb.math.tree.SimpleTreeGenerator;
import nl.jchmb.math.tree.Tree;
import nl.jchmb.math.vector.FloatVectorGenerator;

public class Main {
	public static void main(String[] args) {
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true"); // TMP
		Generator<Float> floatGenerator = new SimpleGenerator<Float>(
				new Float[]{
						-4.0f,
						-3.0f,
						-2.0f,
						-1.0f,
						-0.5f,
						0.0f,
						0.5f,
						1.0f,
						2.0f,
						3.0f,
						4.0f
				}
		);
		LeafGenerator<String, Float> leafGenerator = new LeafGenerator<String, Float>(
				new String[]{"x"},
				0.1f,
				floatGenerator
		);
		BinaryOperatorGenerator<Float> binaryOperatorGenerator = new BinaryOperatorGenerator<Float>(leafGenerator)
				.add(new FloatAddition())
				.add(new FloatMultiplication())
				.add(new FloatDivision());
		BinaryOperatorGenerator<Float> binaryOperatorGenerator2 = new BinaryOperatorGenerator<Float>(leafGenerator, 0.2f)
				.add(new FloatAddition())
				.add(new FloatMultiplication())
				.add(new FloatDivision());
		Environment<Tree<Float>, Tree<Float>, PlanetReplicator<Tree<Float>, Tree<Float>>> environment;
		environment = new SimpleEnvironment<Tree<Float>, Tree<Float>, PlanetReplicator<Tree<Float>, Tree<Float>>>(
				//new LinearFunctionEvaluator(4.0f, 5.0f)
				new PolynomialFunctionEvaluator(3.5f, 2.25f, 1.1f, -0.5f)
		);
		FitnessCache<Tree<Float>, Tree<Float>, PlanetReplicator<Tree<Float>, Tree<Float>>> cache = 
				new FitnessCache<Tree<Float>, Tree<Float>, PlanetReplicator<Tree<Float>, Tree<Float>>>(environment);
		Evolver<Tree<Float>, Tree<Float>, PlanetReplicator<Tree<Float>, Tree<Float>>> evolver = new EvolverRepository<Tree<Float>, Tree<Float>>()
				.getObject(0);
		evolver.setFitnessCache(cache);
		List<Tree<Float>> trees = new ListGenerator<Tree<Float>>(
				new SimpleTreeGenerator<Float>(0.0f),
				5000
		).generate();
		evolver.populate(
				trees,
				new IdentityExpressor<Tree<Float>>()
		);
		evolver.addOperator(
				new PlanetMovementOperator<Tree<Float>, Tree<Float>>(
						new FloatVectorGenerator(
								2,							
								new FloatGenerator(0.02f, -0.01f)
						)
				)
		);
		evolver.addOperator(
				new PlanetTerminatorOperator<Tree<Float>, Tree<Float>>(
						cache,
						environment,
						new Selection<Tree<Float>, Tree<Float>, PlanetReplicator<Tree<Float>, Tree<Float>>>()
							.addLayer(0.20f, 50)
							.addLayer(0.50f, 20)
							.addLayer(0.20f, 10)
				)
		);
		evolver.addOperator(
				new PlanetCloneOperator<Tree<Float>, Tree<Float>>(
						new TreeCloner<Float>(),
						new TreeMutator<Float>(
								0.4f,
								0.2f,
								binaryOperatorGenerator,
								leafGenerator
						),
						new Selection<Tree<Float>, Tree<Float>, PlanetReplicator<Tree<Float>, Tree<Float>>>()
							.addLayer(0.20f, 30)
							.addLayer(0.50f, 20),
						cache,
						environment
				)
		);
		evolver.addOperator(
				new PlanetGeneratorOperator<Tree<Float>, Tree<Float>>(
						new TreeGenerator<Float>(
								binaryOperatorGenerator2
						),
						new PlanetReplicatorFactory<Tree<Float>, Tree<Float>>(
								new FloatVectorGenerator(
										2,
										new FloatGenerator()
								)
						),
						30
				)
		);
		evolver.doStep();
		EvolutionFrame<Tree<Float>, Tree<Float>> frame = new EvolutionFrame<Tree<Float>, Tree<Float>>(
				evolver,
				new Dimension(1000, 800)
		);
		
		new Main().start(frame, evolver);
	}
	
	public void start(EvolutionFrame<Tree<Float>, Tree<Float>> frame, Evolver<Tree<Float>, Tree<Float>, PlanetReplicator<Tree<Float>, Tree<Float>>> evolver) {
		new Thread(new EvolverProcess(frame, evolver)).start();
	}
	
	public class EvolverProcess implements Runnable {
		private EvolutionFrame<Tree<Float>, Tree<Float>> frame;
		private Evolver<Tree<Float>, Tree<Float>, PlanetReplicator<Tree<Float>, Tree<Float>>> evolver;
		
		public EvolverProcess(EvolutionFrame<Tree<Float>, Tree<Float>> frame, Evolver<Tree<Float>, Tree<Float>, PlanetReplicator<Tree<Float>, Tree<Float>>> evolver) {
			this.frame = frame;
			this.evolver = evolver;
		}
		
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(50L);
					evolver.doStep();
					frame.repaint();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				}
			}
		}
	}
}
