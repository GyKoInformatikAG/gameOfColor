package de.gyko.gameofcolors.net.tickTackToe;

import de.gyko.gameofcolors.net.tickTackToe.packets.PlayerJoinRequestPacket;
import de.gyko.gameofcolors.net.tickTackToe.packets.PlayerJoinResponsePacket;
import de.gyko.gameofcolors.net.tickTackToe.packets.PlayerPlacePacket;
import de.gyko.gameofcolors.net.tickTackToe.packets.PlayerWinPacket;
import de.gyko.netLib.packet.Packet;
import de.gyko.netLib.packet.PacketFactory;

/**
 * @author anonymous123-code
 */
public class TickTackToePacketFactory extends PacketFactory {
    @Override
    public Packet getPacket(String id) {
        if (PlayerJoinRequestPacket.PACKET_ID.equals(id)) {
            return new PlayerJoinRequestPacket();
        } else if (PlayerJoinResponsePacket.PACKET_ID.equals(id)) {
            return new PlayerJoinResponsePacket();
        } else if (PlayerPlacePacket.PACKET_ID.equals(id)) {
            return new PlayerPlacePacket();
        } else if (PlayerWinPacket.PACKET_ID.equals(id)) {
            return new PlayerWinPacket();
        }
        return null;
    }
}
