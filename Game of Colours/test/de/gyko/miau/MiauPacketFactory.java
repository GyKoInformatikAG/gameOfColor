package de.gyko.miau;

import de.gyko.netLib.packet.Packet;
import de.gyko.netLib.packet.PacketFactory;

public class MiauPacketFactory extends PacketFactory {
    @Override
    public Packet getPacket(String id) {
        if(id.equals(MiauPacket.PACKET_ID)) return new MiauPacket();
        return null;
    }
}
