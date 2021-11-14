package de.gyko.netLib.Packet;

import de.gyko.netLib.Packet.Packet;

/**
 * Entscheidet, welche Klasse für ein Packet verwendet werden soll.
 *
 * @author anonymous123-code
 */
public abstract class PacketFactory {
    /**
     * Gibt eine nicht initialisiertes Packet Instanz zurueck, welche fuer das Initialisieren im PacketInputStream verwendet wird
     *
     * @param id Die id des empfangenen Packets
     * @return ein Packet mit der Instanz des empfangenen Packets. Ist null, wenn kein Packet gefunden wurde
     */
    public abstract Packet getPacket(String id);

    /**
     * Gibt zurueck, ob diese Factory fuer die angegebene Id ein Packet zurueckgibt.
     * @param id Die id des Packets
     * @return Ob fuer diese Id ein passendes Packet gefunden wurde
     */
    public abstract boolean hasPacket(String id);
}
