package de.gyko.gameofcolors.net.tickTackToe.packets;

import de.gyko.netLib.packet.Packet;
import de.gyko.netLib.packet.field.Field;

/**
 * @author anonymous123-code
 */
public class PlayerJoinRequestPacket extends Packet {
    public static final String PACKET_ID = "plj";
    public PlayerJoinRequestPacket (){
        this.id = PACKET_ID;
    }
}
