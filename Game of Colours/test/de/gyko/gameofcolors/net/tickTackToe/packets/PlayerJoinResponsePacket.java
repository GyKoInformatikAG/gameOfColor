package de.gyko.gameofcolors.net.tickTackToe.packets;

import de.gyko.netLib.packet.Packet;
import de.gyko.netLib.packet.field.ByteField;
import de.gyko.netLib.packet.field.Field;

/**
 * @author anonymous123-code
 */
public class PlayerJoinResponsePacket extends Packet {
    public static final String PACKET_ID = "pjr";
    private final ByteField playerId = new ByteField();

    public PlayerJoinResponsePacket(){
        this.id = PACKET_ID;
    }
    public PlayerJoinResponsePacket(byte playerId){
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
