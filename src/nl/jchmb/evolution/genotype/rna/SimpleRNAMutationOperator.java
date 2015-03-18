package nl.jchmb.evolution.genotype.rna;

import java.util.Random;

import nl.jchmb.evolution.core.Population;
import nl.jchmb.evolution.core.operator.Operator;
import nl.jchmb.evolution.genotype.Base;

public class SimpleRNAMutationOperator implements Operator<SimpleRNA> {
	private Random random;
	private float expansionRatio;
	
	public SimpleRNAMutationOperator() {
		this(0.5f);
	}
	
	public SimpleRNAMutationOperator(float expansionRatio) {
		random = new Random();
		this.expansionRatio = expansionRatio;
	}
	
	@Override
	public void apply(Population<SimpleRNA> population) {
		for (SimpleRNA rna : population) {
			
		}
	}
	
	private void mutate(SimpleRNA rna) {
		int randomOffset = Math.abs(random.nextInt()) % rna.getSize();
		Base base;
		if (random.nextDouble() >= expansionRatio) {
			base = Base.values()[Math.abs(random.nextInt() % 4)];
			rna.insert(randomOffset, base);
		} else {
			rna.remove(randomOffset);
		}
	}
}
