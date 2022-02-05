package de.gyko.gameofcolors.net.packet;

import de.gyko.netLib.packet.Packet;

public class GameEndPacket extends Packet {
    public static final String packetId = "end";

    public GameEndPacket(){
        this.id = packetId;
    }
}
