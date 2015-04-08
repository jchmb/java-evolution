package nl.jchmb.evolution.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import nl.jchmb.evolution.evolver.Evolver;
import nl.jchmb.evolution.gui.evolver.planet.PlanetEvolverView;
import nl.jchmb.evolution.replicator.PlanetReplicator;
import nl.jchmb.evolution.replicator.Replicator;

public class EvolutionFrame<G, P> extends JFrame {
	private PlanetEvolverView<G, P> view;
	private EvolutionMenu menu;
	private Evolver<G, P, PlanetReplicator<G, P>> evolver;
	
	public EvolutionFrame(Evolver<G, P, PlanetReplicator<G, P>> evolver, Dimension dimension) {
		super();
		
		this.evolver = evolver;
		setTitle("Artificial Evolution");
		menu = new EvolutionMenu();
		setJMenuBar(menu);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		view = new PlanetEvolverView<G, P>(dimension, evolver);
		getContentPane().add(view);
		pack();
		setFocusable(true);
		setVisible(true);
	}
}
