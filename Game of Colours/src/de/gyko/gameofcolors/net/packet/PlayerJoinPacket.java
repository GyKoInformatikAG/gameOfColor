package de.gyko.gameofcolors.net.packet;


import de.gyko.gameofcolors.net.packet.field.ColorField;
import de.gyko.gameofcolors.net.packet.field.Field;
import de.gyko.gameofcolors.net.packet.field.StringField;

/**
 * @author anonymous123-code
 */
public class PlayerJoinPacket extends Packet {
    public static final String packetId = "plj";

    public PlayerJoinPacket(){
        this.id = packetId;
    }

    public PlayerJoinPacket(java.lang.String name, java.awt.Color color){
        this();
        playerColor.setContent(color);
        playerName.setContent(name);
    }

    final ColorField playerColor = new ColorField();
    final StringField playerName = new StringField();

    public java.awt.Color getPLayerColor(){
        return playerColor.getContent();
    }
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
