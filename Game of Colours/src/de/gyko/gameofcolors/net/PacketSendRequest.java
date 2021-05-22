package de.gyko.gameofcolors.net;

/*
 * A Request for sending packets
 *
 * @author anonymous123-code
 */
public class PacketSendRequest {
    private final Target target;
    private final Packet packet;

    public enum Target {
        ALL_EXCEPT_CALLER,
        ALL,
        CALLER
    }

    public PacketSendRequest(Target target, Packet packet) {
        this.target = target;
        this.packet = packet;

    }

    public Target getTarget() {
        return target;
    }

    public Packet getPacket() {
        return packet;
    }
}
