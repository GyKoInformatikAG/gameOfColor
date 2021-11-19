package de.gyko.gameofcolors.net.tickTackToe;

import de.gyko.gameofcolors.net.tickTackToe.packets.PlayerJoinRequestPacket;
import de.gyko.gameofcolors.net.tickTackToe.packets.PlayerJoinResponsePacket;
import de.gyko.gameofcolors.net.tickTackToe.packets.PlayerPlacePacket;
import de.gyko.netLib.PacketReceiveEvent;
import de.gyko.netLib.PacketReceiveListener;
import de.gyko.netLib.PacketSendRequest;
import de.gyko.netLib.server.Server;

import java.util.ArrayList;

import static de.gyko.netLib.PacketSendRequest.Target.*;

/**
 * @author anonymous123-code
 */
public class TickTackToeServer {
    static private byte player = 1;

    public static void main(String... args){
        new Server(new PacketReceiveListener() {
            @Override
            public ArrayList<PacketSendRequest> onPacketReceived(PacketReceiveEvent p) {
                ArrayList<PacketSendRequest> result = new ArrayList<>();
                if (p.getPacket() instanceof PlayerPlacePacket) {
                    result.add(new PacketSendRequest(ALL_EXCEPT_CALLER, p.getPacket()));
                } else if (p.getPacket() instanceof PlayerJoinRequestPacket){
                    PlayerJoinResponsePacket packet = new PlayerJoinResponsePacket(player);
                    if(player == 1) player++;
                    else if(player == 2) player = -1;
                    result.add(new PacketSendRequest(CALLER, packet));
                }
                return result;
            }
        }, new TickTackToePacketFactory(), 6001);
    }
}
