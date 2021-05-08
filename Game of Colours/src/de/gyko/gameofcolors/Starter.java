package de.gyko.gameofcolors;

import java.util.logging.Level;
import java.util.logging.Logger;

import de.gyko.gameofcolors.app.GameOfColors;

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
		@SuppressWarnings("unused")
		GameOfColors gameOfColors = new GameOfColors();
		Logger.getLogger("de.gyko.gameofcolours").log(Level.INFO, "Hallo Welt");
	}

}
