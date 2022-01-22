package de.gyko.gameofcolors.gui;

import java.awt.Color;
import java.awt.EventQueue;

import de.gyko.gameofcolors.app.GameOfColors;

/**
 * Eine Klasse zum Test des ConnectingWindow
 * @author Thorsten
 */
public class ConnectingWindowTest {
 
	private ConnectingWindow frame = null;
	
	public ConnectingWindowTest(GameOfColors game) {
		frame = new ConnectingWindow(game);

	}
	
	private void showGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Startet eine Anwendung, die ein ConnectingWindow zeigt.
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		GameOfColors game = new GameOfColors();
		ConnectingWindowTest test = new ConnectingWindowTest(game);
		test.showGUI();
		
		Thread.sleep(3000);
		game.addPlayer(Color.BLUE, "Otto");
		
		Thread.sleep(3000);
		game.addPlayer(Color.RED, "Luzie");
		
		
	}
	
	
	
}
