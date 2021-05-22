package de.gyko.gameofcolors.net;

/*
 * Ein Listener der auf das Empfangen eines Packets reagiert.
 *
 * @author anonymous123-code
 */
public abstract class PacketReceiveListener {
    public abstract PacketSendRequest[] onPacketReceived (PacketReceiveEvent p);
    synchronized PacketSendRequest[] packetReceive (PacketReceiveEvent p) {
        return this.onPacketReceived(p);
    }
}
