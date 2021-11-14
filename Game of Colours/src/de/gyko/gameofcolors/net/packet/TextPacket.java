package de.gyko.gameofcolors.net.packet;

import de.gyko.netLib.packet.Packet;
import de.gyko.netLib.packet.field.Field;
import de.gyko.netLib.packet.field.StringField;

/**
 * Ein Packet, dass einen einfachen Text enthaelt
 *
 * @author anonymous123-code
 */
public class TextPacket extends Packet {
    public static final String packetId = "txt";
    StringField text = new StringField();

    public TextPacket(){
        this.id = packetId;
    }

    /**
     * Erstellt ein neues TextPacket auf Basis eines Textes.
     *
     * @param text der Text des Packets
     */
    public TextPacket(String text){
        this();
        this.text.setContent(text);
    }

    /**
     * Gibt den Text des Packets zurueck.
     *
     * @return den Text
     */
    public String getText() {
        return this.text.getContent();
    }

    @Override
    public Field[] getFields() {
        return new Field[]{
                text
        };
    }
}
