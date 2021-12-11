package de.gyko.miau;

import de.gyko.netLib.PacketReceiveEvent;
import de.gyko.netLib.PacketReceiveListener;
import de.gyko.netLib.PacketSendRequest;
import de.gyko.netLib.server.Server;

import java.util.ArrayList;


public class MiauServer {
    public static void main(String[] args) {
        new Server(new PacketReceiveListener() {
            @Override
            public ArrayList<PacketSendRequest> onPacketReceived(PacketReceiveEvent p) {
                ArrayList<PacketSendRequest> result = new ArrayList<>();
                if (p.getPacket() instanceof MiauPacket) {
                    result.add(new PacketSendRequest(PacketSendRequest.Target.ALL, new MiauPacket()));
                }
                return result;
            }
        }, new MiauPacketFactory(), 14999);
    }
}
