package de.gyko.gameofcolors.net.fake;

import de.gyko.gameofcolors.net.PacketReceiveListener;
import de.gyko.gameofcolors.net.server.ServerThread;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable{
    private final PacketReceiveListener packetReceiveListener;
    private boolean closed = false;
    private final ArrayList<OutputStream> channels = new ArrayList<>();
    int port;

    /**
     * Erstellt und initialisiert einen Server für einen bestimmten Port.
     *
     * @param packetReceiveListener der PacketReceiveListener, der benachrichtigt wird, wenn ein Packet empfangen wurde
     * @param port                  Port für den Server
     */
    public Server(PacketReceiveListener packetReceiveListener, int port) {
        this.port = port;
        this.packetReceiveListener = packetReceiveListener;
    }

    /**
     * Fuehrt den Server-Thread aus. (Nicht aufrufen!)
     */
    @Override
    public void run() {
    }
}
