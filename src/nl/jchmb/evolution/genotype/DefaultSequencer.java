package nl.jchmb.evolution.genotype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

import nl.jchmb.evolution.genotype.rna.SimpleRNA;
import nl.jchmb.utils.OverflowQueue;

public class DefaultSequencer implements Sequencer {
	private int codonSize = 3;
	private Collection<Codon> startCodons;
	private Collection<Codon> stopCodons;
	
	public DefaultSequencer(Collection<Codon> startCodons, Collection<Codon> stopCodons) {
		this.startCodons = startCodons;
		this.stopCodons = stopCodons;
	}
	
	public DefaultSequencer(Codon[] startCodons, Codon[] stopCodons) {
		this(
				Arrays.asList(startCodons),
				Arrays.asList(stopCodons)
		);
	}
	
	public DefaultSequencer() {
		this(
				new Codon[]{
						new Codon(new Base[]{Base.A, Base.T, Base.G}),
						new Codon(new Base[]{Base.G, Base.T, Base.G}),
						new Codon(new Base[]{Base.T, Base.T, Base.G})
				},
				new Codon[]{
						new Codon(new Base[]{Base.T, Base.A, Base.G}),
						new Codon(new Base[]{Base.T, Base.A, Base.A}),
						new Codon(new Base[]{Base.T, Base.G, Base.A})
				}
		);
	}
	
	private Codon buildCodon(Queue<Base> memory) {
		Base[] bases = new Base[codonSize];
		for (int i = 0; i < bases.length; i++) {
			bases[i] = memory.poll();
			memory.add(bases[i]);
		}
		return new Codon(bases);
	}
	
	@Override
	public List<Sequence> sequence(Iterable<Base> iterable) {
		Codon codon = null;
		List<Sequence> sequences = new ArrayList<Sequence>();
		Queue<Base> memory = new OverflowQueue<Base>(codonSize);
		boolean inSequence = false;
		List<Codon> codons = new ArrayList<Codon>();
		
		for (Base base : iterable) {
			memory.offer(base);
			if (memory.size() == codonSize) {
				codon = buildCodon(memory);
			}
			
			if (codon == null) {
				continue;
			}
			
			if (inSequence) {
				 if (stopCodons.contains(codon)) {
					 inSequence = false;
					 if (!codons.isEmpty()) {
						 sequences.add(new Sequence(new ArrayList<Codon>(codons)));
					 	codons.clear();
					 }
				 } else {
					 codons.add(codon);
				 }
				 memory.clear();
			} else if (startCodons.contains(codon)) {
				memory.clear();
				inSequence = true;
			}
			codon = null;
		}
		
		return sequences;
	}
	
	public static void main(String[] args) {
		double a = 2.0d;
		for (int i = 0; i < 1000; i++) {
			a = Math.sqrt(a);
		}
		System.out.println(a);
	}
}