package de.gyko.gameofcolors;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.gyko.gameofcolors.app.Battlefield;
import de.gyko.gameofcolors.app.GameOfColors;
import de.gyko.gameofcolors.gui.MainWindow;
import de.gyko.gameofcolors.net.Client;

/**
 * Diese Klasse startet das Spiel.
 * 
 * @author Thorsten
 *
 */
public class Starter {
	
	Client client;
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
		client = createClient();
		newGame = createGame();
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
	private Client createClient() {
//		client = new Client();
		return null; // TODO
	}

	/**
	 * Erzeugt ein neues Spiel
	 */
	private GameOfColors createGame() {
		GameOfColors einSpiel = null;
		GameOfColors gameOfColors = new GameOfColors();
		gameOfColors.setClient(client);
		Battlefield theBattlefield = new Battlefield(einSpiel);
		gameOfColors.setBattlefield(theBattlefield);
		return gameOfColors;
	}
	
	/**
	 * Erzeugt das GUI für das Spiel und zeigt es.
	 * @param newGame 
	 */
	private void createAndShowMainWindow(GameOfColors newGame) {
		MainWindow mainWindow = new MainWindow(newGame);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				mainWindow.setVisible(true);
			}
		});		
	}


}
