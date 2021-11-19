package de.gyko.gameofcolors.net.tickTackToe.packets;

import de.gyko.netLib.packet.Packet;
import de.gyko.netLib.packet.field.ByteField;
import de.gyko.netLib.packet.field.Field;

/**
 * @author anonymous123-code
 */
public class PlayerPlacePacket extends Packet {
    public static final String PACKET_ID = "plp";
    private final ByteField x = new ByteField();
    private final ByteField y = new ByteField();
    private final ByteField playerId = new ByteField();

    public PlayerPlacePacket(){
        this.id = PACKET_ID;
    }
    public PlayerPlacePacket(byte x, byte y, byte playerId) {
        this();
        this.x.setContent(x);
        this.y.setContent(y);
        this.playerId.setContent(playerId);
    }

    public byte getX() {
        return x.getContent();
    }

    public byte getY() {
        return y.getContent();
    }

    public byte getPlayerId() {
        return playerId.getContent();
    }

    @Override
    protected Field[] getFields() {
        return new Field[]{
                x,
                y,
                playerId
        };
    }
}
