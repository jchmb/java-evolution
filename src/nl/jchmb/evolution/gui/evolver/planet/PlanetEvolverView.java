package nl.jchmb.evolution.gui.evolver.planet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import nl.jchmb.evolution.evolver.Evolver;
import nl.jchmb.evolution.replicator.PlanetReplicator;
import nl.jchmb.math.tree.Tree;

public class PlanetEvolverView<G, P> extends JPanel {
	private Dimension dimension;
	private Evolver<G, P, PlanetReplicator<G, P>> evolver;
	
	public PlanetEvolverView(Dimension dimension, Evolver<G, P, PlanetReplicator<G, P>> evolver) {
		this.dimension = dimension;
		this.evolver = evolver;
		
		setPreferredSize(dimension);
		setFocusable(true);
	}
	
	private Color getColor(PlanetReplicator<G, P> replicator) {
		float fitnessValue = evolver.getFitnessCache().getFitness(replicator);
		int colorValue = (int) ((1.0f - fitnessValue) * 255.0f);
		return new Color(255, colorValue, colorValue);
	}
	
	private float getBestFitness() {
		PlanetReplicator<G, P> replicator = evolver.getFitnessCache().getBestReplicator();
		return replicator == null ? 1.0f : evolver.getFitnessCache().getFitness(replicator);
	}
	
	private int getComplexity(PlanetReplicator<G, P> replicator) {
		if (replicator.getGenotype() instanceof Tree<?>) { // TMP
			G genotype = replicator.getGenotype();
			Tree<?> tree = (Tree<?>) genotype;
			return tree.size();
		}
		return 0;
	}
	
	public void paint(Graphics g) {
		int x, y;
		PlanetReplicator<G, P> r = evolver.getFitnessCache().getBestReplicator();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, (int) dimension.getWidth(), (int) dimension.getHeight());
		List<PlanetReplicator<G, P>> replicators = evolver.getReplicators();
		
		for (PlanetReplicator<G, P> replicator : replicators) {
			g.setColor(getColor(replicator));
			x = (int) (replicator.getPosition().get(0) * dimension.getWidth());
			y = (int) (replicator.getPosition().get(1) * dimension.getHeight());
			g.fillOval(x, y, 5, 5);
		}
		
		g.setColor(Color.WHITE);
		g.drawString("Population size: " + replicators.size(), 25, 25);
		g.drawString("Best fitness: " + getBestFitness() + " (Complexity: " + getComplexity(r) + ")", 25, 50);
		g.drawString("Formula: " + r.getGenotype().toString(), 25, 75);
	}
}