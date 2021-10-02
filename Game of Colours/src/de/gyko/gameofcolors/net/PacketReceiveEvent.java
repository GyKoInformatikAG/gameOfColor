package de.gyko.gameofcolors.net;

/**
 * Ein Event, das bei Empfang eines Packets ausgeloest wird
 *
 * @author anonymous123-code
 */
public class PacketReceiveEvent {
    /**
     * Die Id des Clients
     */
    private int clientId;

    /**
     * Das empfangene Packet
     */
    private Packet packet;

    /**
     * Der Wert, den die ClientId annimmt, wenn sie noch nicht initialisiert wurde
     */
    public static final int CLIENT_ID_NOT_INITIALIZED = Integer.MIN_VALUE;

    /**
     * Der Wert, den die ClientId annimmt, der Sender des Packets der Server war
     */
    public static final int CLIENT_ID_IS_SERVER = Integer.MIN_VALUE + 1;

    /**
     * Erstellt ein neues PacketReceiveEvent auf Basis von Id des Clients und des Packets.
     *
     * @param clientId die Id des Clients
     * @param packet   das Packet
     */
    public PacketReceiveEvent(int clientId, Packet packet) {
        this.clientId = clientId;
        this.packet = packet;
    }

    /**
     * Gibt die Id des Clients zurueck.
     *
     * @return die Id des Clients
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Setzt die Id des Clients
     *
     * @param clientId die neue Id des Clients
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * Gibt das empfangene Packet zurueck.
     *
     * @return das empfangene Packet
     */
    public Packet getPacket() {
        return packet;
    }

    /**
     * Setzt das empfangene Packet
     *
     * @param packet das neue empfangene Packet
     */
    public void setPacket(Packet packet) {
        this.packet = packet;
    }
}
