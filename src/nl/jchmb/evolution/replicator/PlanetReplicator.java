package nl.jchmb.evolution.replicator;

import java.util.ArrayList;
import java.util.List;

import nl.jchmb.evolution.core.Expressor;
import nl.jchmb.evolution.core.Population;
import nl.jchmb.math.vector.FloatVector;
import nl.jchmb.math.vector.Vector;

public class PlanetReplicator<G, P> extends Replicator<G, P> {
	private Vector<Float> position;
	
	public PlanetReplicator(G genotype, Expressor<G, P> expressor) {
		super(genotype, expressor);
	}
	
	public Vector<Float> getPosition() {
		return position;
	}
	
	public void setPosition(Vector<Float> position) {
		this.position = position;
	}
	
	public void walk(Vector<Float> walkVector) {
		position = position.add(walkVector);
		if (position.get(0) < 0) {
			position = position.add(new FloatVector(new float[]{1.0f, 0.0f}));
		}
		if (position.get(1) < 0) {
			position = position.add(new FloatVector(new float[]{0.0f, 1.0f}));
		}
		if (position.get(0) > 1) {
			position = position.subtract(new FloatVector(new float[]{1.0f, 0.0f}));
		}
		if (position.get(1) > 1) {
			position = position.subtract(new FloatVector(new float[]{0.0f, 1.0f}));
		}
	}
	
	public List<PlanetReplicator<G, P>> getNeighbours(Population<G, P, PlanetReplicator<G, P>> population, Float range) {
		List<PlanetReplicator<G, P>> neighbours = new ArrayList<PlanetReplicator<G, P>>();
		for (PlanetReplicator<G, P> replicator : population) {
			if (position.inRange(replicator.position, range)) {
				neighbours.add(replicator);
			}
		}
		return neighbours;
	}
}
