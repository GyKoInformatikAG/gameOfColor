package de.gyko.gameofcolors.app.test;

import java.awt.Color;

import de.gyko.gameofcolors.app.Player;

/**
 * 
 * Die Klasse PlayerTest ist nur zum Testen der Klasse Player gedacht.
 * Sie enthaelt keinen Code, der fuer "Game Of Color" erforderlich ist.
 * 
 * @author Thorsten
 *
 */
public class PlayerTest {
	
	public static void main(String[] args) {
		
		System.out.println("Main-Methode der Klasse PlayerTest ist aufgerufen.");
		
		Player thorsten = new Player();
		
		thorsten.setName("anonymous123-code");
		thorsten.setColor( Color.BLUE );
		
		System.out.println( thorsten.getName() );
		System.out.println( thorsten.getColor() );
		
		System.out.println("Letzte Anweisung in der Main-Methode der Klasse PlayerTest ");
		
	}
	

}
