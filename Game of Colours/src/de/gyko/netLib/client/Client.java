package de.gyko.netLib.client;

import de.gyko.netLib.packet.Packet;
import de.gyko.netLib.PacketReceiveEvent;
import de.gyko.netLib.PacketReceiveListener;
import de.gyko.netLib.PacketSendRequest;
import de.gyko.netLib.packet.PacketFactory;
import de.gyko.netLib.packet.PacketInputStream;
import de.gyko.netLib.packet.PacketOutputStream;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
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
    private final PacketFactory packetFactory;

    private boolean closed = false;
    private boolean initialized = false;

    private PacketOutputStream out;
    private final Lock outLock = new ReentrantLock();

    private static Logger logger = null;

    /**
     * Erstellt und initialisiert einen Client für einen bestimmten Port.
     *
     * @param packetReceiveListener der PacketReceiveListener, der benachrichtigt wird, wenn ein Packet empfangen wurde
     * @param address               die Adresse zum Verbinden
     * @param port                  Port für den Server
     */
    public Client(PacketReceiveListener packetReceiveListener, PacketFactory packetFactory, InetAddress address, int port) {
        if (logger == null) {
            logger = Logger.getLogger("de.gyko.netLib.client");
            logger.setLevel(Level.INFO);
        }
        this.packetFactory = packetFactory;
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
        try (
                Socket socket = new Socket(this.address, this.port);
                PacketOutputStream out = new PacketOutputStream(socket.getOutputStream(), packetFactory);
                PacketInputStream in = new PacketInputStream(socket.getInputStream(), packetFactory)
        ) {
            this.initialized = true;
            logger.fine("Ready");
            this.out = out;
            while (!closed) {
                Packet p = in.next();

                logger.finer("Packet("+p.getId()+") empfangen");
                logger.finest("Packet Inhalt: " + p);

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
                                    out.write(p);
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
     * @throws IllegalStateException, wenn der Client noch nicht fertig initialisiert wurde
     */
    public void sendPacket(Packet p)  {
        if (!initialized) throw new IllegalStateException("Not initialized");
        try {
            outLock.lock();
            try {
                out.write(p);
                out.flush();
            } finally {
                outLock.unlock();
            }
            logger.fine("Sent");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
