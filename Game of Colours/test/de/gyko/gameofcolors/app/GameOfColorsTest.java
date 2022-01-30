package de.gyko.gameofcolors.app;

public class GameOfColorsTest {
	
	
	public static void main(String[] args) {
		
		GameOfColors einKonkretesSpiel = new GameOfColors();
		Battlefield einneuesBattlefield = new Battlefield(einKonkretesSpiel);
		einKonkretesSpiel.setBattlefield(einneuesBattlefield);
		
	}

}
