package nl.jchmb.evolution.genotype;

import java.util.List;

public interface Sequencer {
	public List<Sequence> sequence(Iterable<Base> iterable);
}
