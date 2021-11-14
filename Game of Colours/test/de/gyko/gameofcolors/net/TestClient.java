package de.gyko.gameofcolors.net;

import de.gyko.netLib.PacketReceiveEvent;
import de.gyko.netLib.PacketReceiveListener;
import de.gyko.netLib.PacketSendRequest;
import de.gyko.netLib.client.Client;

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
                    if (line.equalsIgnoreCase("-!exit")) break;
                    client.sendPacket(new TextPacket(line + '\n'));
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }
}
