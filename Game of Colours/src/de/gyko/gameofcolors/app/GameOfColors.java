package de.gyko.gameofcolors.app;

/**
 * Das Spiel GameOfColours
 * 
 * In einem konkreten Spiel dieser Klasse treffen sich zwei bis ... Spieler, 
 * um auf einem Spielfeld zu spielen. 
 *
 * @author noch zu vergeben!
 *
 */
public class GameOfColors {
	
	/** 
	 * Das Spielfeld, auf dem die Spieler ihre Farben setzen.
	 */
	private Battlefield battlefield = null;

	/**
	 * @return the battlefield
	 */
	public Battlefield getBattlefield() {
		return battlefield;
	}

	/**
	 * @param battlefield the battlefield to set
	 */
	public void setBattlefield(Battlefield battlefield) {
		this.battlefield = battlefield;
	}
	

}
