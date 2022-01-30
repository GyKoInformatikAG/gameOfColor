package de.gyko.gameofcolors;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.gyko.gameofcolors.app.Battlefield;
import de.gyko.gameofcolors.app.GameOfColors;
import de.gyko.gameofcolors.gui.ConnectingWindow;

/**
 * Diese Klasse startet das Spiel.
 * 
 * @author Thorsten
 *
 */
public class Starter {
	
	GameOfColors newGame;
	
	/**
	 * Diese Methode erzeugt den Starter des Spiels und ruft seine run-Methode auf.
	 *   
	 * @param args Es werden noch keine Parameter benötigt.
	 */
	public static void main(String[] args) {
		
		(new Starter()).run();
		
	}

	/**
	 * Startet den Starter.
	 */
	private void run() {
		initializeApplication();
		newGame = createGame();
		createAndShowConnectingWindow(newGame);
	}

	/**
	 * Initialisiert die Anwendung
	 */
	private void initializeApplication() {
		Logger.getLogger("de.gyko.gameofcolours").log(Level.INFO, "Hallo Welt");
	}

	/**
	 * Erzeugt ein neues Spiel
	 */
	private GameOfColors createGame() {
		GameOfColors gameOfColors = new GameOfColors();
		Battlefield theBattlefield = new Battlefield(gameOfColors);
		gameOfColors.setBattlefield(theBattlefield);
		return gameOfColors;
	}
	
	/**
	 * Erzeugt das ConnectingWindow und zeigt ihn.
	 * @param newGame 
	 */
	private void createAndShowConnectingWindow(GameOfColors newGame) {
		ConnectingWindow connectingWindow = new ConnectingWindow(newGame);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				connectingWindow.setVisible(true);
			}
		});		
	}	
	

}
