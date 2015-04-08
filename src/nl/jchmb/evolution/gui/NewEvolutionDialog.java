package nl.jchmb.evolution.gui;

import java.awt.Graphics;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class NewEvolutionDialog extends JDialog {
	private Content content;
	
	public NewEvolutionDialog() {
		super();
		
		setTitle("New Evolution");
		content = new Content();
		getContentPane().add(content);
		pack();
	}
	
	private class Content extends JPanel {
		public void repaint(Graphics g) {
			
		}
	}
}