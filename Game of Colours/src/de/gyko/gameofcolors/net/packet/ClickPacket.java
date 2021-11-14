package de.gyko.gameofcolors.net.packet;

import de.gyko.netLib.packet.Packet;
import de.gyko.netLib.packet.field.ColorField;
import de.gyko.netLib.packet.field.Field;
import de.gyko.netLib.packet.field.IntegerField;

import java.awt.*;

/**
 * Das Packet, dass verschickt wird, wenn auf die Spielflaeche geklickt wurde
 *
 * @author anonymous123-code
 */
public class ClickPacket extends Packet {
    public static final String packetId = "clk";

    /**
     * Die X-Koordinate des Klicks
     */
    final IntegerField x = new IntegerField();

    /**
     * Die Y-Koordinate des Klicks
     */
    final IntegerField y = new IntegerField();

    /**
     * Der Spieler, der geklickt hat
     */
    final ColorField color = new ColorField();

    public ClickPacket(){
        this.id = packetId;
    }

    /**
     * Erstellt ein ClickPacket
     * @param x X-Koordinate des Klicks
     * @param y Y-Koordinate des Klicks
     * @param color Farbe des Spielers, der geklickt hat
     */
    public ClickPacket(int x, int y, Color color){
        this();
        this.x.setContent(x);
        this.y.setContent(y);
        this.color.setContent(color);
    }

    /**
     * Gibt den Spieler, der geklickt hat, zurueck
     * @return der Spieler
     */
    public Color getColor(){
        return this.color.getContent();
    }

    /**
     * Gibt die X-Koordinate des Klicks zurueck
     * @return Die X-Koordinate
     */
    public int getX(){
        return this.x.getContent();
    }

    /**
     * Gibt die Y-Koordinate des Klicks zurueck
     * @return Die Y-Koordinate
     */
    public int getY(){
        return this.y.getContent();
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected Field[] getFields() {
        return new Field[]{
                this.x,
                this.y,
                this.color
        };
    }
}
