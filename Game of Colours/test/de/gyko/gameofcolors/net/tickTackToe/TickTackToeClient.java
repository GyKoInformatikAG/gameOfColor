package de.gyko.gameofcolors.net.tickTackToe;

import de.gyko.gameofcolors.net.tickTackToe.packets.PlayerJoinRequestPacket;
import de.gyko.gameofcolors.net.tickTackToe.packets.PlayerJoinResponsePacket;
import de.gyko.gameofcolors.net.tickTackToe.packets.PlayerPlacePacket;
import de.gyko.netLib.PacketReceiveEvent;
import de.gyko.netLib.PacketReceiveListener;
import de.gyko.netLib.PacketSendRequest;
import de.gyko.netLib.client.Client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import static de.gyko.netLib.PacketSendRequest.Target.ALL;

/**
 * @author anonymous123-code
 */
public class TickTackToeClient {
    private static Byte player;
    private static final byte[][] field = new byte[][]{
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };


    public static void main(String... args) {
        try (Scanner in = new Scanner(System.in)) {
            try (
                    Client client = new Client(new PacketReceiveListener() {
                        @Override
                        public ArrayList<PacketSendRequest> onPacketReceived(PacketReceiveEvent p) {
                            ArrayList<PacketSendRequest> requests = new ArrayList<>();
                            if (p.getPacket() instanceof PlayerJoinResponsePacket) {
                                if (((PlayerJoinResponsePacket) p.getPacket()).getPlayerId() == -1) {
                                    Thread.currentThread().interrupt();
                                    return requests;
                                }
                                player = ((PlayerJoinResponsePacket) p.getPacket()).getPlayerId();
                                if (player == 1) {
                                    printField();
                                    int pos = in.nextInt();
                                    int x = pos % 3;
                                    int y = pos / 3;
                                    field[x][y] = player;
                                    requests.add(new PacketSendRequest(ALL, new PlayerPlacePacket((byte) x, (byte) y, player)));
                                }
                            } else if (p.getPacket() instanceof PlayerPlacePacket) {
                                System.out.println("Empfangen");
                                int x = ((PlayerPlacePacket) p.getPacket()).getX();
                                int y = ((PlayerPlacePacket) p.getPacket()).getY();
                                field[x][y] = ((PlayerPlacePacket) p.getPacket()).getPlayerId();
                                printField();
                                int pos = in.nextInt();
                                x = pos % 3;
                                y = pos / 3;
                                field[x][y] = player;
                                requests.add(new PacketSendRequest(ALL, new PlayerPlacePacket((byte) x, (byte) y, player)));
                            }

                            return requests;
                        }
                    }, new TickTackToePacketFactory(), InetAddress.getByName(in.nextLine()), 14999)
            ) {
                client.sendPacket(new PlayerJoinRequestPacket());
            } catch (UnknownHostException e) {e.printStackTrace();}
        }
    }

    private static void printField() {
        for (int y = 0; y < field[0].length; y++) {
            for (byte[] bytes : field) {
                System.out.print((bytes[y] > 0 ? (bytes[y] == 1 ? "X" : "O") : " ") + " ");
            }
            System.out.println();
        }
    }
}
