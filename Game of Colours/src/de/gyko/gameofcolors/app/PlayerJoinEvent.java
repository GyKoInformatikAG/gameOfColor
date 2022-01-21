/**
 * 
 */
package de.gyko.gameofcolors.app;

import java.awt.Color;
import java.util.EventObject;

/**
 * Diese Klasse bildet das Ereignis ab, dass ein Spieler hinzukommt.
 * 
 * @author Thorsten
 */
public class PlayerJoinEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	/** Die Farbe des Spielers, der hinzukommt */
	private Color playerColor = null;
	
	/**
	 * Erzeugt das Event f�r den hinzukommenden Spieler
	 * @param player Der Spieler wird �ber seine Farbe identifiziert.
	 * @param source Das Objekt, in dem das Event urspr�nglich aufgetreten ist.
	 */
	public PlayerJoinEvent(Object source, Color player) {
		super(source);
		this.playerColor = player;
	}
	
	/**
	 * @return Die Farbe des Spielers, der hinzukommt.
	 */
	public Color getPlayerColor() {
		return playerColor;
	}
	
}	