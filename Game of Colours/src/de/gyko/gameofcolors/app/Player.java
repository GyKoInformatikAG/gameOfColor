package de.gyko.gameofcolors.app;

import java.awt.Color;

/**
 * Von der Klasse Player werden Objekte des Typs Player instanziiert.
 * 
 * Ein Player ist derjenige, der an einem Spiel teilnehmen kann.
 * Ein Player hat eine Farbe.  
 *
 */
public class Player {
	
	/**
	 * Der Name des Spielers
	 */
	private String name = "";
	
	/**
	 * Die Farbe des Spielers, 
	 * sie identifiziert einen Spieler, das bedeutet, dass ein Spieler
	 * anhand seiner Farbe erkannt werden kann. 
	 */
	private Color color = null;

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	} 

}
