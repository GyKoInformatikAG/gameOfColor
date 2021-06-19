package de.gyko.gameofcolors.net.client;

import de.gyko.gameofcolors.net.PacketReceiveEvent;
import de.gyko.gameofcolors.net.PacketReceiveListener;
import de.gyko.gameofcolors.net.PacketSendRequest;
import de.gyko.gameofcolors.net.TextPacket;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ein einfacher Test Client
 *
 * @author anonymous123-code
 */
public class TestClient {
    public static void main(String... args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Adress");
            String address = in.nextLine();
            System.out.println("Port");
            int port = in.nextInt();
            try (Client client = new Client(new PacketReceiveListener() {
                @Override
                public ArrayList<PacketSendRequest> onPacketReceived(PacketReceiveEvent p) {
                    if (p.getPacket() instanceof TextPacket)
                        System.out.println(((TextPacket) p.getPacket()).getText());
                    else System.out.println("Unknown packet");
                    return null;
                }
            }, InetAddress.getByName(address), port)
            ) {
                in.nextLine();
                while (true) {
                    String line = in.nextLine();
                    client.sendPacket(new TextPacket(line));
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }
}
