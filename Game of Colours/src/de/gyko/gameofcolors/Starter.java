package de.gyko.gameofcolors;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.gyko.gameofcolors.app.Battlefield;
import de.gyko.gameofcolors.app.GameOfColors;
import de.gyko.gameofcolors.gui.MainWindow;

/**
 * Diese Klasse startet das Spiel.
 * 
 * @author Thorsten
 *
 */
public class Starter {
	
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
		initializeNet();
		GameOfColors newGame = createGame();
//		createAndShowConnectingDialog(newGame);
		createAndShowMainWindow(newGame);
	}

	/**
	 * Initialisiert die Anwendung
	 */
	private void initializeApplication() {
		Logger.getLogger("de.gyko.gameofcolours").log(Level.INFO, "Hallo Welt");
	}

	/**
	 * Initialisiert die Netzwerkschicht
	 */
	private void initializeNet() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Erzeugt ein neues Spiel
	 */
	private GameOfColors createGame() {
		GameOfColors gameOfColors = new GameOfColors();
		Battlefield theBattlefield = new Battlefield();
		gameOfColors.setBattlefield(theBattlefield);
		return gameOfColors;
	}
	
	/**
	 * Erzeugt das GUI für das Spiel und zeigt es.
	 * @param newGame 
	 */
	private void createAndShowMainWindow(GameOfColors newGame) {
		MainWindow mainWindow = new MainWindow();
		// TODO: Dem mainWindow das Spiel übergeben.
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				mainWindow.setVisible(true);
			}
		});		
	}


}
