package de.gyko.gameofcolors.net;

import de.gyko.gameofcolors.net.packet.Packet;

import java.nio.charset.StandardCharsets;

/**
 * Ein einfaches Text Packet
 *
 * @author anonymous123-code
 */
public class TextPacket extends Packet {
    String text;
    public static final String packetId = "txt";

    /**
     * Erstellt ein neues TextPacket auf Basis eines Textes.
     *
     * @param text der Text des Packets
     */
    public TextPacket(String text) {
        super();
        // TODO: aktuell nur fuer Testzwecke, richtig machen
        this.text = text;
        byte[] rawContent = new byte[3 + text.getBytes(StandardCharsets.UTF_8).length];
        rawContent[0] = packetId.getBytes(StandardCharsets.UTF_8)[0];
        rawContent[1] = packetId.getBytes(StandardCharsets.UTF_8)[1];
        rawContent[2] = packetId.getBytes(StandardCharsets.UTF_8)[2];
        byte[] sBytes = text.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(sBytes, 0, rawContent, 3, sBytes.length);
        this.rawContent = rawContent;
        id = new String(new byte[]{rawContent[0], rawContent[1], rawContent[2]});
    }

    /**
     * Gibt den rohen Inhalt des Packets zurueck.
     *
     * @return den rohe Inhalt
     */
    @Override
    public byte[] getRawContent() {
        // TODO: Workaround fuer Testzwecke, Spaeter entfernen
        byte[] out = new byte[this.rawContent.length - 3];
        System.arraycopy(this.rawContent, 3, out, 0, this.rawContent.length - 3);
        return out;
    }

    /**
     * Gibt den Text des Packets zurueck.
     *
     * @return den Text
     */
    public String getText() {
        return text;
    }
}
