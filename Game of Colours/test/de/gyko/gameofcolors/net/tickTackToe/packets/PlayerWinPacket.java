package de.gyko.gameofcolors.net.tickTackToe.packets;

import de.gyko.netLib.packet.Packet;
import de.gyko.netLib.packet.field.ByteField;
import de.gyko.netLib.packet.field.Field;

/**
 * @author anonymous123-code
 */
public class PlayerWinPacket extends Packet {
    public static final String PACKET_ID = "plw";
    private final ByteField playerId = new ByteField();

    public PlayerWinPacket(){
        this.id = PACKET_ID;
    }
    public PlayerWinPacket(byte playerId){
        this();
        this.playerId.setContent(playerId);
    }

    public byte getPlayerId() {
        return playerId.getContent();
    }

    @Override
    protected Field[] getFields() {
        return new Field[]{
                playerId
        };
    }
}
