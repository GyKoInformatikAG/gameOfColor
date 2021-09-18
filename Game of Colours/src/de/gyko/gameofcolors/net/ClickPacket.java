package de.gyko.gameofcolors.net;

import de.gyko.gameofcolors.app.Player;

/**
 * Das Packet, dass verschickt wird, wenn auf die Spielflaeche geklickt wurde
 */
public class ClickPacket extends Packet{
    /**
     * Der Spieler, der geklickt hat
     */
    private Player player;
    /**
     * Die X-Koordinate des Klicks
     */
    private int x;
    /**
     * Die Y-Koordinate des Klicks
     */
    private int y;
    protected final static String packetId = "clk";

    /**
     * Erstellt ein ClickPacket
     * @param x X-Koordinate des Klicks
     * @param y Y-Koordinate des Klicks
     * @param player Spieler, der geklickt hat
     */
    public ClickPacket(int x, int y, Player player){
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Player getPlayer() {
        return player;
    }
}
