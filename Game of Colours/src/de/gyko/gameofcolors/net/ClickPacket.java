package de.gyko.gameofcolors.net;

import de.gyko.netLib.packet.Packet;

import java.awt.*;

/**
 * Das Packet, dass verschickt wird, wenn auf die Spielflaeche geklickt wurde
 *
 * @deprecated Bewegt zu de.gyko.gameofcolors.net.packet.ClickPacket
 */
public class ClickPacket extends Packet {
    /**
     * Der Spieler, der geklickt hat
     */
    private Color color;
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
     * @param color Farbe des Spielers, der geklickt hat
     */
    public ClickPacket(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * Gibt die X-Koordinate des Klicks zurueck
     * @return Die X-Koordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gibt die X-Koordinate des Klicks zurueck
     * @return Die X-Koordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Gibt den Spieler, der geklickt hat, zurueck
     * @return der Spieler
     */
    public Color getColor() {
        return color;
    }
}
