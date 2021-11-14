package de.gyko.netLib.client;

import de.gyko.gameofcolors.net.*;
import de.gyko.netLib.Packet.Packet;
import de.gyko.netLib.PacketReceiveEvent;
import de.gyko.netLib.PacketReceiveListener;
import de.gyko.netLib.PacketSendRequest;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private boolean initialized = false;

    private OutputStream out;
    private final Lock outLock = new ReentrantLock();

    private static Logger logger = null;

    /**
     * Erstellt und initialisiert einen Client für einen bestimmten Port.
     *
     * @param packetReceiveListener der PacketReceiveListener, der benachrichtigt wird, wenn ein Packet empfangen wurde
     * @param address               die Adresse zum Verbinden
     * @param port                  Port für den Server
     */
    public Client(PacketReceiveListener packetReceiveListener, InetAddress address, int port) {
        if (logger == null) {
            logger = Logger.getLogger("de.gyko.netLib.client");
            logger.setLevel(Level.FINE);
        }
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
            this.initialized = true;
            System.out.println("Ready");
            this.out = out;
            while (!closed) {

                //TODO: Packets richtig parsen

                String input = scanner.nextLine();

                Packet p = new TextPacket(input);
                //noinspection ConstantConditions
                if (false) p = new Packet() {};

                logger.finer("Packet("+p.getId()+") empfangen");
                logger.finest("Packet Inhalt: " + Arrays.toString(p.getRawContent()));
                //noinspection ConstantConditions
                if (p instanceof TextPacket) logger.finest("Packet Inhalt: " + ((TextPacket) p).getText());

                PacketReceiveEvent event = new PacketReceiveEvent(PacketReceiveEvent.CLIENT_ID_IS_SERVER, p);
                synchronized (packetReceiveListener) {
                    ArrayList<PacketSendRequest> requests = packetReceiveListener.onPacketReceived(event);
                    if (requests != null) for (PacketSendRequest request : requests) {
                        switch (request.getTarget()) {
                            case ALL:
                            case CALLER:
                                logger.finest("PacketSendRequest");
                                outLock.lock();
                                try {
                                    out.write(request.getPacket().getRawContent());
                                    out.flush();
                                } finally {
                                    outLock.unlock();
                                }
                                break;
                            default:
                                System.err.println();
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
    public void close(){
        this.closed = true;
    }

    /**
     * Sendet ein Packet
     * @param p das zu sendende Packet
     * @throws IllegalStateException wenn der Client noch nicht fertig initialisiert wurde
     */
    public void sendPacket(Packet p)  {
        if (!initialized) throw new IllegalStateException("Not initialized");
        try {
            outLock.lock();
            try {
                out.write(p.getRawContent());
                out.flush();
            } finally {
                outLock.unlock();
            }
            System.out.println("sent");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
