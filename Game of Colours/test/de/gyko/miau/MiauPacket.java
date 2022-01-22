package de.gyko.miau;

import de.gyko.netLib.packet.Packet;

public class MiauPacket extends Packet {
    public static final String PACKET_ID = "plm";
    public MiauPacket() {
        this.id = PACKET_ID;
    }
}
