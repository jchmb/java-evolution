package nl.jchmb.evolution.phenotype;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nl.jchmb.evolution.core.Expressor;
import nl.jchmb.evolution.genotype.binary.Bit;
import nl.jchmb.utils.OverflowQueue;

public class BitExpressor<P> implements Expressor<List<Bit>, P> {
	private Bit[] startSequence;
	private Collection<PhenotypeBuilder<Bit, P>> builders;
	
	private BitExpressor(Bit[] startSequence) {
		builders = new ArrayList<PhenotypeBuilder<Bit, P>>();
		this.startSequence = startSequence;
	}
	
	public BitExpressor() {
		this(new Bit[]{Bit.ONE, Bit.ZERO, Bit.ONE, Bit.ZERO});
	}
	
	public void addBuilder(PhenotypeBuilder<Bit, P> builder) {
		builders.add(builder);
	}
	
	@Override
	public P express(List<Bit> genotype) {
		int i = 0;
		OverflowQueue<Bit> buffer = new OverflowQueue<Bit>(startSequence.length);
		
		for (Bit bit : genotype) {
			if (buffer.size() == startSequence.length) {
				buffer.clear();
			}
			buffer.add(bit);
		}
		
		finishBuilders();
		return phenotype;
	}
}
