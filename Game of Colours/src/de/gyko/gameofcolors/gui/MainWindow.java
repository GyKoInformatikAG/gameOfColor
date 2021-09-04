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

	public MainWindow() {

		JPanel panel = new PlayingSurface();
		getContentPane().add(panel, BorderLayout.CENTER);
		setBounds(100, 100, 900, 700);
	}

	public MainWindow(GameOfColors Game) {
		this();

		//TODO Kontrolle
		game = Game;



	}

}

