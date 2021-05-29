package de.gyko.gameofcolors.net;

/**
 * Ein Request, ein Packet zu senden
 *
 * @author anonymous123-code
 */
public class PacketSendRequest {
    /**
     * Das Ziel des Packets
     */
    private final Target target;
    /**
     * Das Packet
     */
    private final Packet packet;

    /**
     * Die moeglichen Ziele eines Packets
     */
    public enum Target {
        ALL_EXCEPT_CALLER,
        ALL,
        CALLER
    }

    /**
     * Erstellt einen neuen Packet SendRequest.
     * @param target das Ziel des Packets
     * @param packet das Packet
     */
    public PacketSendRequest(Target target, Packet packet) {
        this.target = target;
        this.packet = packet;
    }

    /**
     * Gibt das Ziel des Packets zurueck.
     * @return das Ziel
     */
    public Target getTarget() {
        return target;
    }

    /**
     * Gibt das Packet zurueck.
     * @return das Packet
     */
    public Packet getPacket() {
        return packet;
    }
}
