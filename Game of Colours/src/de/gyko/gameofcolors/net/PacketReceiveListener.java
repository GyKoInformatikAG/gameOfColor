package de.gyko.gameofcolors.net;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Ein Listener der auf das Empfangen eines Packets reagiert.
 *
 * @author anonymous123-code
 */
public abstract class PacketReceiveListener {
    /**
     * Wird aufgerufen wenn ein Packet empfangen wurde.
     *
     * @param p das PacketReceiveEvent
     * @return die Reaktionen  auf das Packet, sollten vom Aufrufer verarbeitet werden
     */
    public abstract ArrayList<PacketSendRequest> onPacketReceived(PacketReceiveEvent p);
}
