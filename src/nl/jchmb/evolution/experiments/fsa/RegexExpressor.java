package nl.jchmb.evolution.experiments.fsa;

import nl.jchmb.evolution.phenotype.BitExpressor;
import nl.jchmb.evolution.phenotype.fsa.FSA;

public class RegexExpressor extends BitExpressor<FSA<String>> {
	public RegexExpressor(int stateSize, String[] alphabet) {
		super();
		
		addBuilder(new StateBuilder(stateSize, alphabet));
	}
}