package de.gyko.miau;

import de.gyko.netLib.PacketReceiveEvent;
import de.gyko.netLib.PacketReceiveListener;
import de.gyko.netLib.PacketSendRequest;
import de.gyko.netLib.client.Client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class MiauClient {
    public static MuhPlayer player = new MuhPlayer();
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            try (
                    Client c = new Client(new PacketReceiveListener() {
                        @Override
                        public ArrayList<PacketSendRequest> onPacketReceived(PacketReceiveEvent p) {
                            if (p.getPacket() instanceof MiauPacket) player.play();
                            return new ArrayList<>();
                        }
                    }, new MiauPacketFactory(), InetAddress.getByName(in.nextLine()), 14999)
            ) {
                boolean running = true;
                while (running) {
                    String i = in.nextLine();
                    switch (i) {
                        case "miau":
                            c.sendPacket(new MiauPacket());
                            break;
                        case "quit":
                            running = false;
                    }
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

    }
}
