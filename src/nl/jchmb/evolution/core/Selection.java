package nl.jchmb.evolution.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import nl.jchmb.evolution.replicator.Replicator;

public class Selection<G, P, R extends Replicator<G, P>> {
	private List<Layer> layers;
	
	public Selection() {
		layers = new ArrayList<Layer>();
	}
	
	public Selection<G, P, R> addLayer(float portion, int count) {
		layers.add(new Layer(portion, count));
		return this;
	}
	
	public List<R> select(List<R> replicators) {
		float p = 0.0f, q = 0.0f;
		boolean started = false;
		R currentReplicator;
		List<R> newReplicators = new ArrayList<R>();
		Queue<R> replicatorQueue = new LinkedList<R>(replicators);
		Queue<Layer> queue = new LinkedList<Layer>(layers);
		List<R> buffer = new ArrayList<R>();
		Layer layer = null;
		while (!replicatorQueue.isEmpty()) {
			currentReplicator = replicatorQueue.poll();
			p += 1 / ((float) replicators.size());
			buffer.add(currentReplicator);
			if (layer == null || p >= layer.portion) {
				if (started) {
					newReplicators.addAll(select(buffer, layer));
					p -= layer.portion;
					buffer.clear();
				} else {
					started = true;
				}
				if (!queue.isEmpty()) {
					layer = queue.poll();
				}
			}
			
			
		}
		return newReplicators;
	}
	
	private List<R> select(List<R> replicators, Layer layer) {
		if (layer.count >= replicators.size()) {
			return replicators;
		}
		Collections.shuffle(replicators);
		List<R> selection = new ArrayList<R>();
		for (int i = 0; i < layer.count; i++) {
			selection.add(replicators.get(i));
		}
		return selection;
	}
	
	private class Layer {
		private float portion;
		private int count;
		
		public Layer(float portion, int count) {
			this.portion = portion;
			this.count = count;
		}
	}
}