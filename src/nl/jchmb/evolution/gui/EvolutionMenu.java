package nl.jchmb.evolution.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class EvolutionMenu extends JMenuBar {
	private JMenu startMenu;
	
	public EvolutionMenu() {
		super();
		
		addStartMenu();
	}
	
	private void addStartMenu() {
		startMenu = new JMenu("Start");
		
		addNewButton(startMenu);
		addOpenButton(startMenu);
		addSaveButton(startMenu);
		startMenu.addSeparator();
		addExitButton(startMenu);
		
		add(startMenu);
	}
	
	private void addNewButton(JMenu menu) {
		JMenuItem newButton = new JMenuItem("New");
		menu.add(newButton);
	}
	
	private void addOpenButton(JMenu menu) {
		JMenuItem openButton = new JMenuItem("Open");
		menu.add(openButton);
	}
	
	private void addSaveButton(JMenu menu) {
		JMenuItem saveButton = new JMenuItem("Save");
		menu.add(saveButton);
	}
	
	private void addExitButton(JMenu menu) {
		JMenuItem exitButton = new JMenuItem("Exit");
		exitButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(-1);
			}
		});
		menu.add(exitButton);
	}
}
