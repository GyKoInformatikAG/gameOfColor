package de.gyko.netLib;

import java.util.ArrayList;

/**
 * Ein Listener der auf das Empfangen eines Packets reagiert.
 *
 * @author anonymous123-code
 */
public interface PacketReceiveListener {
    /**
     * Wird aufgerufen, wenn ein Packet empfangen wurde.
     *
     * @param p das PacketReceiveEvent
     * @return die Reaktionen auf das Packet, sollten vom Aufrufer verarbeitet werden
     */
    ArrayList<PacketSendRequest> onPacketReceived(PacketReceiveEvent p);
}
