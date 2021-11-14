package de.gyko.netLib.server;

import de.gyko.netLib.PacketReceiveListener;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
        new Thread(this).start();
    }

    /**
     * Fuehrt den Server-Thread aus. (Nicht aufrufen!)
     */
    @Override
    public void run() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (!closed) {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket, packetReceiveListener, channels);
                threadPool.submit(serverThread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
