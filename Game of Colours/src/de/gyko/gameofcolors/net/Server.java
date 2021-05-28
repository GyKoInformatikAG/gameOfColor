package de.gyko.gameofcolors.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Spielt die Rolle eines Servers
 *
 * @author anonymous123-code
 */
public class Server implements Runnable{

    private PacketReceiveListener packetReceiveListener;
    Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
    private boolean closed = false;
    private final ArrayList<OutputStream> channels = new ArrayList<>();
    int port;

    /**
     *
     *
     * @param port Port für den Server
     */
    public Server (PacketReceiveListener packetReceiveListener,int port){
        this.port = port;
        this.packetReceiveListener = packetReceiveListener;
        new Thread(this).start();
    }

    public static void main(String... args){
        new Server(new PacketReceiveListener() {
            @Override
            public PacketSendRequest[] onPacketReceived(PacketReceiveEvent p) {
                return new PacketSendRequest[0];
            }
        },6000);
    }


    @Override
    public void run() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (!closed) {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket, packetReceiveListener, channels, map);
                threadPool.submit(serverThread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
