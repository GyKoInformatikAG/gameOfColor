package de.gyko.gameofcolors.net;

import de.gyko.gameofcolors.net.packet.TextPacket;
import de.gyko.netLib.packet.Packet;
import de.gyko.netLib.packet.PacketFactory;

/**
 * @author anonymous123-code
 */
public class TestPacketFactory extends PacketFactory {
    @Override
    public Packet getPacket(String id) {
        if (TextPacket.packetId.equals(id)) {
            return new TextPacket();
        }
        return null;
    }
}
