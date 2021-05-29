package de.gyko.gameofcolors.net.server;

import de.gyko.gameofcolors.net.PacketReceiveEvent;
import de.gyko.gameofcolors.net.PacketReceiveListener;
import de.gyko.gameofcolors.net.PacketSendRequest;
import de.gyko.gameofcolors.net.TextPacket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ein Thread der die Server <-> Client Connection handelt.
 *
 * @author anonymous123-code
 */
public class ServerThread implements Runnable {

    private final Socket socket;
    private boolean closed = false;
    private int clientId = PacketReceiveEvent.CLIENT_ID_NOT_INITIALIZED;
    private final PacketReceiveListener packetReceiveListener;
    private ArrayList<OutputStream> channels;

    /**
     * Erstellt einen neuen ServerThread.
     *
     * @param socket                der zu handelnde socket
     * @param packetReceiveListener der PacketReceiveListener, der bei Empfang aufgerufen wird.
     * @param channels              Die Ausgabekanaele
     */
    public ServerThread(Socket socket, PacketReceiveListener packetReceiveListener, ArrayList<OutputStream> channels) {
        this.socket = socket;
        this.packetReceiveListener = packetReceiveListener;
        this.channels = channels;
    }

    /**
     * Fuehrt den Thread aus. (Nicht aufrufen!)
     */
    @Override
    public void run() {
        try (OutputStream out = socket.getOutputStream();
             InputStream in = socket.getInputStream();
             Scanner scanner = new Scanner(in, "UTF-8")
        ) {
            channels.add(out);
            while (!closed) {
                //TODO: Packets richtig parsen

                String input = scanner.nextLine();

                PacketReceiveEvent event = new PacketReceiveEvent(clientId, new TextPacket(input));
                synchronized (packetReceiveListener) {
                    ArrayList<PacketSendRequest> requests = packetReceiveListener.onPacketReceived(event);
                    clientId = event.getClientId();
                    for (PacketSendRequest request : requests) {
                        switch (request.getTarget()) {
                            case ALL:
                                for (OutputStream stream : channels) {
                                    stream.write(request.getPacket().getRawContent());
                                    stream.flush();
                                }
                                break;
                            case CALLER:
                                out.write(request.getPacket().getRawContent());
                                out.flush();
                                break;
                            case ALL_EXCEPT_CALLER:
                                for (OutputStream stream : channels) {
                                    if (stream != out) {
                                        stream.write(request.getPacket().getRawContent());
                                        stream.flush();
                                    }
                                }
                                break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {
            }
        }
    }
}
