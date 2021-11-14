package de.gyko.gameofcolors.net.fake;

import java.io.Closeable;
import java.io.OutputStream;
import java.net.InetAddress;

import de.gyko.netLib.Packet.Packet;
import de.gyko.netLib.PacketReceiveListener;

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
    }

    /**
     * Fuehrt den Client-Thread aus. (Nicht aufrufen!)
     */
    @Override
    public void run() {
    }

    /**
     * Schließt den Client
     */
    public void close() {
    }

    /**
     * Sendet ein Packet
     *
     * @param p das zu sendende Packet
     * @throws IllegalStateException wenn der Client noch nicht fertig initialisiert wurde
     */
    public void sendPacket(Packet p) {
    }

}
