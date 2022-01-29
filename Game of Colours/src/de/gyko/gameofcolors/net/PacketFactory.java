package de.gyko.gameofcolors.net;

import de.gyko.gameofcolors.net.packet.*;
import de.gyko.netLib.packet.Packet;

public class PacketFactory extends de.gyko.netLib.packet.PacketFactory{
    @Override
    public Packet getPacket(String id) {
        switch (id) {
            case ClickPacket.packetId:
                return new ClickPacket();
            case PlayerJoinPacket.packetId:
                return new PlayerJoinPacket();
        }
        return null;
    }
}
