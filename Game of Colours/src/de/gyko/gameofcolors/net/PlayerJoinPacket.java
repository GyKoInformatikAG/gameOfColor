package de.gyko.gameofcolors.net;

import java.awt.*;
import java.nio.charset.StandardCharsets;

import static de.gyko.gameofcolors.Utility.uint;

/**
 * Das Packet, das ein Server sendet, wenn ein neuer Spieler dem Spiel beitritt.
 *
 * @author anonymous123-code
 */
public class PlayerJoinPacket extends Packet {
    /**
     * Der Name des Spielers
     */
    private final String playerName;
    /**
     * Die Farbe des Spielers
     */
    private final Color playerColor;

    /**
     * Erstellt ein neues PlayerJoinPacket auf Basis von dem uebergebenen content.
     *
     * @param content der Inhalt des rohen Packets
     * @throws IllegalArgumentException wenn content nicht den Anforderungen entspricht, d.h. zu kurz oder zu lang ist oder nicht die richtige packetId enthaelt
     */
    public PlayerJoinPacket(byte... content) throws IllegalArgumentException {
        super(content);
        if (!id.equals("plj")) throw new IllegalArgumentException("wrong packetId");
        if (content.length < 7) {
            throw new IllegalArgumentException("too little bytes");
        }

        int nameLength = uint(rawContent[3]);
        if (nameLength <= 0) {
            throw new IllegalArgumentException("nameLength must not be 0");
        }
        if (rawContent.length < 7 + nameLength) {
            throw new IllegalArgumentException("too little bytes");
        }
        if (rawContent.length > 7 + nameLength) {
            throw new IllegalArgumentException("too much bytes");
        }

        byte[] playerNameBytes = new byte[nameLength];
        System.arraycopy(rawContent, 4, playerNameBytes, 0, nameLength);
        playerName = new String(playerNameBytes);

        playerColor = new Color(uint(rawContent[nameLength + 4]), uint(rawContent[nameLength + 5]), uint(rawContent[nameLength + 6]));
    }

    /**
     * Erstellt ein neues PlayerJoinPacket auf basis von Spielername und -farbe.
     *
     * @param playerName  der Name des Spielers
     * @param playerColor die Farbe des Spielers
     */
    public PlayerJoinPacket(String playerName, Color playerColor) {
        this.playerColor = playerColor;
        this.playerName = playerName;
        this.id = "plj";
        this.rawContent = new byte[7 + this.playerName.length()];
        this.rawContent[0] = 0x70;
        this.rawContent[1] = 0x6c;
        this.rawContent[2] = 0x6a;
        this.rawContent[3] = (byte) this.playerName.length();
        this.rawContent[4 + this.playerName.length()] = (byte) this.playerColor.getRed();
        this.rawContent[5 + this.playerName.length()] = (byte) this.playerColor.getGreen();
        this.rawContent[6 + this.playerName.length()] = (byte) this.playerColor.getBlue();
        System.arraycopy(this.playerName.getBytes(StandardCharsets.UTF_8), 0, this.rawContent, 4, this.playerName.length());
    }

    /**
     * Gibt die Spielerfarbe zurueck.
     *
     * @return die Farbe des Spielers
     */
    public Color getPlayerColor() {
        return playerColor;
    }

    /**
     * Gibt den Spielernamen zurueck.
     *
     * @return den Namen des Spielers
     */
    public String getPlayerName() {
        return playerName;
    }
}
