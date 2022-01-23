package de.gyko.gameofcolors.gui;

import java.awt.Color;

/**
 * Die Aufz�hlung der Farben, die in dem Spiel m�glich sind.
 * 
 * @author Thorsten
 */
public enum PlayerColor {
	
	RED("rot", Color.RED),
	BLUE("blau", Color.BLUE),
	GREEN("gr\u00fcn", Color.GREEN),
	YELLOW("gelb", Color.YELLOW);
	
	private final String text;
	private final Color color;
	
	PlayerColor(String text, Color color) {
		this.text = text;
		this.color = color;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
	/** 
	 * Die Farbe der Spielerfarbe
	 * @return Die Farbe
	 */
	public Color getColor() {
		return color;
	}

}
