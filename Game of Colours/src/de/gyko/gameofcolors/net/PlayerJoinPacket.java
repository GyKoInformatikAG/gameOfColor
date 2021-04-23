package de.gyko.gameofcolors.net;

import java.awt.*;
import java.nio.charset.StandardCharsets;

import static de.gyko.gameofcolors.Utility.uint;

/*
 * The Packet the Server sends when a new Player joins
 *
 * @author anonymous123-code
 */
public class PlayerJoinPacket extends Packet{
    private final String playerName;
    private final Color playerColor;
    public PlayerJoinPacket(byte... content) {
        super(content);
        if(!id.equals("plj")) throw new IllegalArgumentException("wrong packetId");
        if(content.length < 7){
            throw new IllegalArgumentException("too little bytes");
        }

        int nameLength = uint(rawContent[3]);
        if(nameLength <= 0) {
            throw new IllegalArgumentException("nameLength must not be 0");
        }
        if(rawContent.length < 7 + nameLength) {
            throw new IllegalArgumentException("too little bytes");
        }
        if(rawContent.length > 7 + nameLength) {
            throw new IllegalArgumentException("too much bytes");
        }

        byte[] playerNameBytes = new byte[nameLength];
        System.arraycopy(rawContent, 4, playerNameBytes, 0, nameLength);
        playerName = new String(playerNameBytes);

        playerColor = new Color(uint(rawContent[nameLength + 4]), uint(rawContent[nameLength + 5]), uint(rawContent[nameLength + 6]));
    }

    public PlayerJoinPacket(String playerName, Color playerColor){
        this.playerColor = playerColor;
        this.playerName = playerName;
        this.id = "plj";
        this.rawContent = new byte[7 + this.playerName.length()];
        this.rawContent[0] = 0x70;
        this.rawContent[1] = 0x6c;
        this.rawContent[2] = 0x6a;
        this.rawContent[3] = (byte) this.playerName.length();
        this.rawContent[4+this.playerName.length()] = (byte) this.playerColor.getRed();
        this.rawContent[5+this.playerName.length()] = (byte) this.playerColor.getGreen();
        this.rawContent[6+this.playerName.length()] = (byte) this.playerColor.getBlue();
        System.arraycopy(this.playerName.getBytes(StandardCharsets.UTF_8), 0, this.rawContent, 4, this.playerName.length());
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public String getPlayerName() {
        return playerName;
    }
}
