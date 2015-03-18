package nl.jchmb.evolution.core.operator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nl.jchmb.evolution.core.Evaluator;
import nl.jchmb.evolution.core.Expressor;
import nl.jchmb.evolution.core.Population;

public class SelectionOperator<G, P> implements Operator<G> {
	private Evaluator<P> evaluator;
	private Expressor<G, P> expressor;
	private float survivalRatio;
	
	public SelectionOperator(float survivalRatio, Expressor<G, P> expressor, Evaluator<P> evaluator) {
		this.survivalRatio = survivalRatio;
		this.expressor = expressor;
		this.evaluator = evaluator;
	}

	@Override
	public Population<G> apply(Population<G> population) {
		int i = 0;
		int count = (int) (survivalRatio * ((float) population.getSize()));
		List<G> newGenotypes = population.getGenotypes();
		Collections.sort(newGenotypes, new FitnessComparator());
		return new Population<G>(newGenotypes.subList(0, count));
	}
	
	private class FitnessComparator implements Comparator<G> {
		@Override
		public int compare(G g1, G g2) {
			float f1, f2;
			
			f1 = evaluator.evaluate(expressor.express(g1));
			f2 = evaluator.evaluate(expressor.express(g2));
			
			return f1 == f2 ? 0 : (f1 > f2 ? 1 : -1);
		}
	}
}
