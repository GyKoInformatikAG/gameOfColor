package de.gyko.gameofcolors.net.packet;


import de.gyko.netLib.packet.Packet;
import de.gyko.netLib.packet.field.ColorField;
import de.gyko.netLib.packet.field.Field;
import de.gyko.netLib.packet.field.StringField;

/**
 * Das Packet, das ein Server sendet, wenn ein neuer Spieler dem Spiel beitritt.
 *
 * @author anonymous123-code
 */
public class PlayerJoinPacket extends Packet {
    public static final String packetId = "plj";

    /**
     * Die Farbe des Spielers
     */
    final ColorField playerColor = new ColorField();

    /**
     * Der Name des Spielers
     */
    final StringField playerName = new StringField();


    public PlayerJoinPacket(){
        this.id = packetId;
    }

    /**
     * Erstellt ein neues PlayerJoinPacket auf Basis von Spielername und -farbe.
     *
     * @param name  der Name des Spielers
     * @param color die Farbe des Spielers
     */
    public PlayerJoinPacket(java.lang.String name, java.awt.Color color){
        this();
        playerColor.setContent(color);
        playerName.setContent(name);
    }

    /**
     * Gibt die Spielerfarbe zurueck.
     *
     * @return die Farbe des Spielers
     */
    public java.awt.Color getPLayerColor(){
        return playerColor.getContent();
    }

    /**
     * Gibt den Spielernamen zurueck.
     *
     * @return den Namen des Spielers
     */
    public java.lang.String getPlayerName(){
        return playerName.getContent();
    }

    @Override
    public Field[] getFields() {
        return new Field[]{
                playerName,
                playerColor
        };
    }
}
