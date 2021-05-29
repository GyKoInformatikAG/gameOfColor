package de.gyko.gameofcolors.app;

import java.awt.Color;

public class BattlefieldTest {
	
	

	public static void main(String[] args) {
		
		Battlefield einSchlachtfeld = new Battlefield();
		
		einSchlachtfeld.setHeight(5);
		einSchlachtfeld.setWidth(10);
		
		einSchlachtfeld.setColorAt(1, 2, Color.BLUE);
		
		// Mit folgender Anweisung soll in irgendeiner Form erkennbar werden, dass das Feld auf der 
		// Koordinate (1 | 2) die Farbe blau hat. 
		System.out.println( einSchlachtfeld.getColorAt(1, 2) ); 
		
		
	}
	
}
