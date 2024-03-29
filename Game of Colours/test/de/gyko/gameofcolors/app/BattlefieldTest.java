package de.gyko.gameofcolors.app;

import java.awt.Color;

public class BattlefieldTest {
	
	

	public static void main(String[] args) {
		
		GameOfColors einSpiel = null;
		Battlefield einSchlachtfeld = new Battlefield(einSpiel);
		
		einSchlachtfeld.setHeight(5);
		einSchlachtfeld.setWidth(10);
		
		einSchlachtfeld.setColorAt(1, 9, Color.blue);
		einSchlachtfeld.setColorAt(1, 2, Color.red);
		
		// Mit folgender Anweisung soll in irgendeiner Form erkennbar werden, dass das Feld auf der 
		// Koordinate (1 | 2) die Farbe blau hat. 
		System.out.println( einSchlachtfeld.getColorAt(1, 2) + " "+einSchlachtfeld.getColorAt(1, 9) ); 
		
		
	}
	
}
