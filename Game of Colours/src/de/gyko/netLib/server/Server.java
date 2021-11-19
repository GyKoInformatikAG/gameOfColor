package de.gyko.netLib.server;

import de.gyko.netLib.PacketReceiveListener;
import de.gyko.netLib.packet.PacketFactory;
import de.gyko.netLib.packet.PacketOutputStream;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Nimmt die Rolle eines Servers ein.
 *
 * @author anonymous123-code
 */
public class Server implements Runnable {

    private final PacketReceiveListener packetReceiveListener;
    private final ArrayList<PacketOutputStream> channels = new ArrayList<>();
    private final PacketFactory packetFactory;

    private volatile boolean closed = false;
    int port;

    /**
     * Erstellt und initialisiert einen Server für einen bestimmten Port.
     *
     * @param packetReceiveListener der PacketReceiveListener, der benachrichtigt wird, wenn ein Packet empfangen wurde
     * @param port                  Port für den Server
     */
    public Server(PacketReceiveListener packetReceiveListener, PacketFactory packetFactory, int port) {
        this.port = port;
        this.packetFactory = packetFactory;
        this.packetReceiveListener = packetReceiveListener;
        new Thread(this).start();
    }

    /**
     * Fuehrt den Server-Thread aus. (Nicht aufrufen!)
     */
    @Override
    public void run() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setSoTimeout(1000);
            while (!closed) {
                try {
                    Socket socket = serverSocket.accept();
                    ServerThread serverThread = new ServerThread(socket, packetReceiveListener, channels, packetFactory);
                    threadPool.submit(serverThread);
                } catch (SocketTimeoutException ignored) {}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
