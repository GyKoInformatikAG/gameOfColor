package de.gyko.gameofcolors.net.client;

import de.gyko.gameofcolors.net.*;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Der Client
 *
 * @author anonymous123-code
 */
public class Client implements Runnable, Closeable {

    private final PacketReceiveListener packetReceiveListener;
    private final int port;
    private final InetAddress address;

    private boolean closed = false;

    private final Queue<Packet> toWrite = new ConcurrentLinkedQueue<>();

    private OutputStream out;

    /**
     * Erstellt und initialisiert einen Client für einen bestimmten Port.
     *
     * @param packetReceiveListener der PacketReceiveListener, der benachrichtigt wird, wenn ein Packet empfangen wurde
     * @param address               die Adresse zum Verbinden
     * @param port                  Port für den Server
     */
    public Client(PacketReceiveListener packetReceiveListener, InetAddress address, int port) {
        this.port = port;
        this.address = address;
        this.packetReceiveListener = packetReceiveListener;
        new Thread(this).start();
    }

    /**
     * Fuehrt den Client-Thread aus. (Nicht aufrufen!)
     */
    @Override
    public void run() {
        try (Socket socket = new Socket(this.address, this.port);
             OutputStream out = socket.getOutputStream();
             InputStream in = socket.getInputStream();
             Scanner scanner = new Scanner(in, "UTF-8")) {
            System.out.println("Ready");
            this.out = out;
            while (!closed) {
                while (toWrite.peek() != null){
                    out.write(toWrite.poll().getRawContent());
                    out.flush();
                }

                //TODO: Packets richtig parsen
                if (in.available() > 0) {
                    String input = scanner.nextLine();

                    PacketReceiveEvent event = new PacketReceiveEvent(PacketReceiveEvent.CLIENT_ID_IS_SERVER, new TextPacket(input));
                    synchronized (packetReceiveListener) {
                        ArrayList<PacketSendRequest> requests = packetReceiveListener.onPacketReceived(event);
                        if (requests != null) for (PacketSendRequest request : requests) {
                            switch (request.getTarget()) {
                                case ALL:
                                case CALLER:
                                    toWrite.add(request.getPacket());
                                    break;
                                default:
                                    System.err.println();
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Schließt den Client
     */
    public void close() {
        this.closed = true;
    }

    /**
     * Sendet ein Packet, sobald der socket fertig initialisiert wurde
     *
     * @param p das zu sendende Packet
     */
    public void sendPacket(Packet p) {
        toWrite.add(p);
    }

}
