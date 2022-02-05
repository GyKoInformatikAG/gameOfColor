package de.gyko.gameofcolors.net.packet;

import de.gyko.netLib.packet.Packet;

public class GameStartPacket extends Packet {
    public static final String packetId = "sta";

    public GameStartPacket(){
        this.id = packetId;
    }
}
