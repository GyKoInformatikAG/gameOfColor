/**
 * 
 */
package de.gyko.gameofcolors.app;

/**
 * Der Status zeigt, in welcher Phase das Spiel sich gerade befindet.
 * 
 * @author Thorsten
 */
public enum State {
	
	/**
	 * Für das Spiel ist noch nicht klar, ob der Spieler
	 * Host sein möchte oder ein Mitspieler.
	 */
	NO_STATE_YET("Noch kein Spiel er\u00F6ffnet / noch keinem Spiel beigetreten"),
	
	/**
	 * Der Spieler hat sich zwar schon entschieden, ob er Host ist oder
	 * Mitspieler, aber der Host des Spiels hat das Spiel noch nicht
	 * gestartet.
	 */
	WAITING_FOR_START("Warten auf Mitspieler"),
	
	/**
	 * Das Spiel läuft.
	 */
	GAMING("Das Spiel läuft");
	
	private String text;

	State(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}	

}
