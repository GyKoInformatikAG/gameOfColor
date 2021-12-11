package de.gyko.gameofcolors.app;

import de.gyko.netLib.client.Client;

/**
 * Das Spiel GameOfColours
 * 
 * In einem konkreten Spiel dieser Klasse treffen sich zwei bis ... Spieler, 
 * um auf einem Spielfeld zu spielen. 
 * 
 * Außerdem verwaltet/kennt das Spiel den "Client", über den es Nachrichten an 
 * andere Spiele auf anderen Rechner schicken kann.
 *
 * @author Sophia
 *
 */
public class GameOfColors {
	
	/** 
	 * Das Spielfeld, auf dem die Spieler ihre Farben setzen.
	 */
	private Battlefield battlefield = new Battlefield(this);
	
	/**
	 * Der Client, der für das Versenden für Paketen (Objekte der Klasse "Packet") sorgt.
	 */
	private Client client = null;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

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
