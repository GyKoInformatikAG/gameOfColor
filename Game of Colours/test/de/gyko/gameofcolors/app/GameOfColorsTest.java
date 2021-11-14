package de.gyko.gameofcolors.app;

import de.gyko.netLib.client.Client;

public class GameOfColorsTest {
	
	
	public static void main(String[] args) {
		
		GameOfColors einKonkretesSpiel = new GameOfColors();

		Client einneuerClient = new Client(null, null, null, 0);
		
		Battlefield einneuesBattlefield = new Battlefield(einKonkretesSpiel);
		
		einKonkretesSpiel.setClient(einneuerClient);
		
		einKonkretesSpiel.setBattlefield(einneuesBattlefield);
		
	}

}
