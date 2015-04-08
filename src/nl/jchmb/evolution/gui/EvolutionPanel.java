package nl.jchmb.evolution.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import nl.jchmb.evolution.evolver.Evolver;

public class EvolutionPanel extends JPanel {
	private Dimension size;
	private Evolver<?, ?, ?> evolver;
	
	public EvolutionPanel(Evolver<?, ?, ?> evolver, Dimension size) {
		this.evolver = evolver;
		setPreferredSize(size);
		setFocusable(true);
	}
	
	public void repaint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, (int) size.getWidth(), (int) size.getHeight());
		
	}
}
