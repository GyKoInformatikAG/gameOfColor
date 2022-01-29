package de.gyko.gameofcolors.net;

import de.gyko.gameofcolors.net.event.ClickEvent;
import de.gyko.gameofcolors.net.event.PlayerJoinEvent;
import de.gyko.gameofcolors.net.packet.ClickPacket;
import de.gyko.gameofcolors.net.packet.PlayerJoinPacket;
import de.gyko.netLib.PacketSendRequest;
import de.gyko.netLib.packet.Packet;

import java.net.InetAddress;
import java.util.ArrayList;

public class Client {
    private final InetAddress ip;
    private final int port;
    private final ArrayList<ClientListener> listeners = new ArrayList<>();
    private de.gyko.netLib.client.Client client;

    public Client(InetAddress ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public void start() {
        this.client = new de.gyko.netLib.client.Client(p -> {
            if (p.getPacket() instanceof ClickPacket) {
                final ClickEvent e = new ClickEvent(((ClickPacket) p.getPacket()).getColor(), ((ClickPacket) p.getPacket()).getX(), ((ClickPacket) p.getPacket()).getY());
                ArrayList<PacketSendRequest> response = new ArrayList<>();
                listeners.forEach((clientListener)-> response.addAll(clientListener.onClick(e)));
                return response;
            } else if (p.getPacket() instanceof PlayerJoinPacket) {
                final PlayerJoinEvent e = new PlayerJoinEvent(((PlayerJoinPacket) p.getPacket()).getPLayerColor(), ((PlayerJoinPacket) p.getPacket()).getPlayerName());
                ArrayList<PacketSendRequest> response = new ArrayList<>();
                listeners.forEach((clientListener)-> response.addAll(clientListener.onPlayerJoin(e)));
                return response;
            }
            return null;
        }, new PacketFactory(), ip, port);
    }

    public void addListener(ClientListener listener) {
        this.listeners.add(listener);
    }

    public void send(Packet p) {
        this.client.sendPacket(p);
    }
}
