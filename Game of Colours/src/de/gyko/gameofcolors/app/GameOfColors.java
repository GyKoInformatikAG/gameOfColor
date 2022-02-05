package de.gyko.gameofcolors.app;

import java.awt.Color;
import java.awt.EventQueue;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.EventListenerList;

import de.gyko.gameofcolors.gui.MainWindow;
import de.gyko.gameofcolors.net.Client;


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
	 * Der Status, den das Spiel gerade hat.
	 */
	private State state = State.NO_STATE_YET;
	
	/**
	 * Die Liste der Mitspieler, die außer dem Spieler selbst am Spiel teilnehmen. Die Liste enthält nicht
	 * den Spieler selbst.
	 */
	private List<Player> playerList = new ArrayList<Player>();
	
	/**
	 * Der Spieler selbst.
	 */
	private Player myself = null;
	
	/** 
	 * Das Spielfeld, auf dem die Spieler ihre Farben setzen.
	 */
	private Battlefield battlefield = new Battlefield(this);

	/**
	 * Die Liste der Listener, die auf PlayerEvents lauschen.
	 * @see PlayerListener
	 */
	private EventListenerList listenerList = new EventListenerList();	
	
    /**
     * Die Klasse kann beobachtet werden, ob sich ihre
     * Eigenschaften ändern.
     */
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }	
	
	/**
	 * Der Client, der für das Versenden von Paketen (Objekte der Klasse "Packet") sorgt.
	 */
	private Client client = null;

	/**
	 * Gibt an, ob das Spiel als Host gestartet wird. Wenn es nicht
	 * als Host gestartet wird, dann ist der Spieler ein Mitspieler.
	 */
	private boolean host = false;

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
	 * Fügt dem Spiel einen Mitspieler hinzu.
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
	            ((PlayerListener)listeners[i+1]).onPlayerJoin(new PlayerJoinEvent(this, player.getColor()));
	        }
	    }
	}	
	
	/**
	 * Erzeugt das GUI für das Spiel und zeigt es.
	 */
	private void createAndShowMainWindow() {
		MainWindow mainWindow = new MainWindow(this);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				mainWindow.setVisible(true);
			}
		});		
	}	
	
	/**
	 * @return Der aktuelle Status des Spiels
	 */
	public State getState() {
		return state;
	}

	/**
	 * @return Ist der Spieler der Host?
	 */
	public boolean isHost() {
		return host;
	}

	/** 
	 * Das Spiel wird als Host gestartet und es können Mitspieler beitreten.
	 * 
	 * @param color Die Farbe des Spielers, der das Spiel gerade öffnet
	 * @param name Der Name des Spielers, der das Spiel gerade öffnet
	 */
	public void openAsHost(Color color, String name) {

		
		host = true; 						// Sei Host
		myself = new Player(color, name);	// Halte fest, wer hier spielt	
		
		// Falls das Spiel bislang als Mitspieler geöffnet wurde, 
		// beende die Verbindung zum bisherigen Host.
		// TODO 
		
		// Behandle die Netzwerkschicht, damit dieses Spiel ein Host wird.
		// (Erzeuge also einen Server oder sage dem Client, dass er einen Server erzeugen soll.)
		// TODO
		
		// Setze den neuen Status:
		setState(State.WAITING_FOR_START);
		
	}

	public void joinHost(Color color, String name) {
		host = false; 						// Sei kein Host
		myself = new Player(color, name);   // Halte fest, wer hier spielt
		
		// Falls das Spiel bislang als Host geöffnet wurde, 
		// beende die Verbindung zu bisherigen Mitspielern.
		// TODO
		
		// Erzeuge einen Client mit der angegebenen Host-Adresse, falls möglich.
		// TODO: Progressbar einbauen!
		// ggf. Exception werfen.
		
		// Hole die Liste der Mitspieler vom Client
		// TODO
		
		// Prüfe, ob die Farbe schon belegt ist.
		// ggf. Exception werfen.
		
		// Setze den neuen Status:
		setState(State.WAITING_FOR_START);
	}

	/**
	 * Ändert den Status des Spiels.
	 * Dies ist eine private Methode, da der Spielstatus durch die 
	 * Klasse selbst verwaltet wird.
	 * 
	 * @param newState
	 */
	private void setState(State newState) {
		State oldState = this.state;
		this.state = newState;
		pcs.firePropertyChange("state", oldState, newState);
	}

	
	/**
	 * Startet das Spiel, indem das das ConnectingWindow geschlossen und das MainWindow angezeigt wird.
	 * 
	 * Die Methode ist private, da sie entweder als Folge eines Lauschens auf ein Event von der 
	 * Netzwerkschicht aufgerufen wird, oder weil der Host das Spiel gestartet hat.
	 * 
	 * TODO: Verweis auf die State-Listener-Methode
	 * @see GameOfColors#sendStart()
	 */
	private void start() {
		// Löse das ConnectingWindow
		// TODO
		
		// Setze den Status auf GAMING
		// TODO
		// setState(State.GAMING);

		createAndShowMainWindow(); 
	}
	

	/**
	 * Der Host ruft diese Methode auf, wenn er das Spiel starten möchte.
	 */
	public void sendStart() {
		// Rufe auf der Netzwerkschicht die "Spiel-Starten!"-Methode auf.
		// TODO

		// Ändere das GUI und los gehts:
		start();
	}

}
