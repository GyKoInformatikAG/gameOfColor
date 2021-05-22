package de.gyko.gameofcolors.net;

/*
 * Wird bei Empfang eines Packets ausgelöst
 *
 * @author anonymous123-code
 */
public class PacketReceiveEvent {
    private int clientId;
    private Packet packet;
    public static final int CLIENT_ID_NOT_INITIALIZED = Integer.MIN_VALUE;
    public static final int CLIENT_ID_IS_SERVER = Integer.MIN_VALUE+1;


    public PacketReceiveEvent(int clientId, Packet packet) {
        this.clientId = clientId;
        this.packet = packet;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }
}
