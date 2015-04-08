package nl.jchmb.evolution.evolver;

import nl.jchmb.evolution.replicator.Replicator;

public class SimpleEvolver<G, P, R extends Replicator<G, P>> extends BaseEvolver<G, P, R> {
	private int maxGenerations;
	private int currentGeneration;
	private volatile boolean done = false;
	
	public SimpleEvolver(int maxGenerations) {
		super();
		this.maxGenerations = maxGenerations;
	}
	
	public SimpleEvolver() {
		this(-1);
	}
	
	@Override
	public void doStep() {
		super.doStep();
		currentGeneration++;
	}
	
	@Override
	protected boolean isDone() {
		if (maxGenerations < 0) {
			return done;
		}
		return currentGeneration >= maxGenerations || done;
	}
	
	public synchronized void setDone(boolean done) {
		this.done = done;
	}
}
