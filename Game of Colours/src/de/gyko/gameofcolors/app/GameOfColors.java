package de.gyko.gameofcolors.app;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.EventListenerList;

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
	 * Die Liste der Spieler, die am Spiel teilnehmen.
	 */
	private List<Player> playerList = new ArrayList<Player>();
	
	/** 
	 * Das Spielfeld, auf dem die Spieler ihre Farben setzen.
	 */
	private Battlefield battlefield = new Battlefield(this);

	/**
	 * Die Liste der Listener, die auf PlayerEvents lauschen.
	 * @see PlayerJoinEvent
	 * @see PlayerLeaveEvent
	 */
	private EventListenerList listenerList = new EventListenerList();	
	
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
	
	/**
	 * Fügt dem Spiel einen Spieler hinzu.
	 * @param color Die Farbe des Spielers
	 * @param name Der Name des Spielers
	 */
	public void addPlayer(Color color, String name) {
		Player p = new Player(color, name);
		playerList.add(p);
		firePlayerJoin(p);
	}

	/**
	 * Gibt den Spieler zurück, der die Farbe color hat.
	 * 
	 * @param color Die Farbe, über die der Spieler identifiziert wird.
	 * @return Der über die Farbe color identifizierte Spieler
	 */
	public Player getPlayerByColor(Color color) {
		for (Player p : playerList) {
			if (p.getColor().equals(color)) return p;
		}
		return null;
	}
	
	/**
	 * Entfernt den Spieler aus dem Spiel, der die Farbe color hat. 
	 * @param color Die Farbe, über die der Spieler identifiziert wird.
	 */
	public void removePlayer(Color color) {
		// TODO
	}

	/**
	 * Registriert einen PlayerListener am Spiel
	 * 
	 * @see EventListenerList
	 * @param listener
	 */
	public void addPlayerListener( PlayerListener listener ) {
		listenerList.add( PlayerListener.class, listener );
	}

	/**
	 * Deregistriert ("löscht" bzw. "löst") einen PlayerListener vom Spiel
	 * 
	 * @see EventListenerList
	 * @param listener
	 */
	public void removePlayerListener( PlayerListener listener ) {
		listenerList.remove( PlayerListener.class, listener );
	}
	
	/**
	 * Benachrichtigt alle Listener, dass ein Spieler hinzugekommen ist.
	 *
	 * @see EventListenerList
	 * @param player Der Spieler, der hinzugekommen ist.
	 */ 
	protected void firePlayerJoin(Player player) {
	    Object[] listeners = listenerList.getListenerList();
	    for (int i = listeners.length-2; i>=0; i-=2) {
	        if (listeners[i]==PlayerListener.class) {
	            ((PlayerListener)listeners[i+1]).playerJoins(new PlayerJoinEvent(this, player.getColor()));
	        }
	    }
	}	

}
