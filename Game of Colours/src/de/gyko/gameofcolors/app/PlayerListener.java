/**
 * 
 */
package de.gyko.gameofcolors.app;

import java.util.EventListener;

/**
 * Ein PlayerListener ist ein Objekt, das auf Ereignisse hören kann,
 * die Player betreffen. Es verarbeitet diese Ereignisse.
 * 
 * @author Thorsten
 */
public interface PlayerListener extends EventListener {
	
	/**
	 * In dieser Methode wird das PlayerJoinEvent-Ereignis verarbeitet
	 * 
	 * @see PlayerJoinEvent
	 * @param pje
	 */
	public void playerJoins(PlayerJoinEvent pje);
	
	/**
	 * In dieser Methode wird das PlayerLeaveEvent-Ereignis verarbeitet
	 * 
	 * @see PlayerLeaveEvent
	 * @param ple
	 */
	public void playerLeave(PlayerLeaveEvent ple);

}
