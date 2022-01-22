package de.gyko.gameofcolors.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.gyko.gameofcolors.app.GameOfColors;


/**
 * 
 * @author barna
 *
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame {


	private GameOfColors game;
	
	
	public MainWindow(GameOfColors game) {
		this.game = game;
		
		JPanel panel = new PlayingSurface(game.getBattlefield());
		getContentPane().add(panel, BorderLayout.CENTER);
		setBounds(420, 150, 1000, 700);
	}

}

