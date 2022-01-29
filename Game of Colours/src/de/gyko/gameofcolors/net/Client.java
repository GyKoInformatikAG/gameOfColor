package de.gyko.gameofcolors.net;

import java.net.InetAddress;

public class Client {
    public Client(){
    }

    public void setConnectionTarget(InetAddress ip, int port) {

    }

    public void start() {
        new de.gyko.netLib.client.Client();

    }

}
