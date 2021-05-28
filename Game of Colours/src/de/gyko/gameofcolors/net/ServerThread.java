package de.gyko.gameofcolors.net;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;

/*
 * A Thread wich handles on server <-> client connection
 *
 * @author anonymous123-code
 */
public class ServerThread implements Runnable{

    private final Socket socket;
    private boolean closed = false;
    private int clientId = PacketReceiveEvent.CLIENT_ID_NOT_INITIALIZED;
    private final PacketReceiveListener packetReceiveListener;
    private ArrayList<OutputStream> channels;
    Map<String, String> map;

    public ServerThread(Socket socket, PacketReceiveListener packetReceiveListener, ArrayList<OutputStream> channels, Map<String, String> map){
        this.socket = socket;
        this.packetReceiveListener = packetReceiveListener;
        this.map = map;
        this.channels = channels;
    }

    @Override
    public void run() {
        try (OutputStream out = socket.getOutputStream();
             InputStream in = socket.getInputStream();
             PrintWriter outputStreamWriter = new PrintWriter(out, true);
             Scanner scanner = new Scanner(in, StandardCharsets.UTF_8)
        ){
            channels.add(out);
            while (!closed) {
                //TODO: Packets richtig parsen

                String input = scanner.nextLine();

                PacketReceiveEvent event = new PacketReceiveEvent(clientId, new TextPacket(input));
                synchronized (packetReceiveListener){
                    PacketSendRequest[] requests = packetReceiveListener.onPacketReceived(event);
                    clientId = event.getClientId();
                    for (PacketSendRequest request: requests) {
                        switch (request.getTarget()){
                            case ALL -> {
                                for (OutputStream stream: channels) {
                                    stream.write(request.getPacket().getRawContent());
                                    stream.flush();
                                }
                            }
                            case CALLER -> {
                                out.write(request.getPacket().getRawContent());
                                out.flush();
                            }
                            case ALL_EXCEPT_CALLER -> {
                                for (OutputStream stream: channels) {
                                    if (stream != out) {
                                        stream.write(request.getPacket().getRawContent());
                                        stream.flush();
                                    }
                                }
                            }
                        }
                    }
                }

                // TODO: Noch zum testen, Später löschen(bzw umziehen)
                String[] inputSplit = input.split(" ");
                if(inputSplit.length < 2){
                    outputStreamWriter.println(input);
                } else if (inputSplit[0].equalsIgnoreCase("set")) {
                    if (inputSplit.length == 2){
                        map.remove(inputSplit[1]);
                        Color c = new Color(0x36393f);
                    } else {
                        map.put(inputSplit[1], input.substring(inputSplit[0].length()+inputSplit[1].length()+2));
                    }
                } else if (inputSplit[0].equalsIgnoreCase("get")){
                    String result = map.get(inputSplit[1]);
                    outputStreamWriter.println(result!=null?result:"ERROR: Key not found");
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
