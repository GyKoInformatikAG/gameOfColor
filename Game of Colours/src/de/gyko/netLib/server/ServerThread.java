package de.gyko.netLib.server;

import de.gyko.netLib.PacketReceiveEvent;
import de.gyko.netLib.PacketReceiveListener;
import de.gyko.netLib.PacketSendRequest;
import de.gyko.netLib.packet.Packet;
import de.gyko.netLib.packet.PacketFactory;
import de.gyko.netLib.packet.PacketInputStream;
import de.gyko.netLib.packet.PacketOutputStream;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Ein Thread der die Server <-> Client Connection handelt.
 *
 * @author anonymous123-code
 */
public class ServerThread implements Runnable {

    private final Socket socket;
    private final PacketReceiveListener packetReceiveListener;
    private final PacketFactory packetFactory;
    private final ArrayList<PacketOutputStream> channels;

    private int clientId = PacketReceiveEvent.CLIENT_ID_NOT_INITIALIZED;

    /**
     * Erstellt einen neuen ServerThread.
     *
     * @param socket                der zu handelnde socket
     * @param packetReceiveListener der PacketReceiveListener, der bei Empfang aufgerufen wird.
     * @param channels              Die Ausgabekanaele
     */
    public ServerThread(
            Socket socket,
            PacketReceiveListener packetReceiveListener,
            ArrayList<PacketOutputStream> channels,
            PacketFactory packetFactory
    ) {
        this.socket = socket;
        this.packetFactory = packetFactory;
        this.packetReceiveListener = packetReceiveListener;
        this.channels = channels;
    }

    /**
     * Fuehrt den Thread aus. (Nicht aufrufen!)
     */
    @Override
    public void run() {
        try (
                PacketOutputStream out = new PacketOutputStream(socket.getOutputStream(), this.packetFactory);
                PacketInputStream in = new PacketInputStream(socket.getInputStream(), this.packetFactory)
        ) {
            socket.setSoTimeout(500);
            channels.add(out);
            while (!Thread.interrupted()) {
                Packet next = in.next();
                if (!in.hasNext()) break;
                if (next != null) {
                    PacketReceiveEvent event = new PacketReceiveEvent(clientId, next);

                    synchronized (packetReceiveListener) {
                        ArrayList<PacketSendRequest> requests = packetReceiveListener.onPacketReceived(event);
                        clientId = event.getClientId();
                        for (PacketSendRequest request : requests) {
                            switch (request.getTarget()) {
                                case ALL:
                                    for (PacketOutputStream stream : channels) {
                                        stream.write(request.getPacket());
                                        stream.flush();
                                    }
                                    break;
                                case CALLER:
                                    out.write(request.getPacket());
                                    out.flush();
                                    break;
                                case ALL_EXCEPT_CALLER:
                                    for (PacketOutputStream stream : channels) {
                                        if (stream != out) {
                                            stream.write(request.getPacket());
                                            stream.flush();
                                        }
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {}
        }
    }
}
