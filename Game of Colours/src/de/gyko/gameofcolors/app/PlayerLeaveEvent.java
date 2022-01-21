package de.gyko.gameofcolors.app;

import java.awt.Color;
import java.util.EventObject;

/**
 * Diese Klasse bildet das Ereignis ab, dass ein Spieler das Spiel verlässt.
 * 
 * @author Thorsten
 */

public class PlayerLeaveEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	/** Die Farbe des Spielers, der hinzukommt */
	private Color playerColor = null;
	
	/**
	 * Erzeugt das Event für den wegfallenden Spieler
	 * @param player Der Spieler wird über seine Farbe identifiziert.
	 * @param source Das Objekt, in dem das Event ursprünglich aufgetreten ist.
	 */
	public PlayerLeaveEvent(Object source, Color player) {
		super(source);
		this.playerColor = player;
	}
	
	/**
	 * @return Die Farbe des Spielers, der das Spiel verlässt.
	 */
	public Color getPlayerColor() {
		return playerColor;
	}
	
	
}
