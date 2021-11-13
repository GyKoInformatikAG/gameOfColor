package de.gyko.gameofcolors.net.packet.field;

/**
 * Ein Feld in einem Packet.
 */
public interface Field<T> {
    /**
     * Gibt die Laenge des fuer das Feld benoetigten Headers zurueck.
     * Der header sollte nur verwendet werden, um die Laenge des Bodies zu bestimmen.
     * Wird waehrend der Initialisierung eines Packets auf Basis eines InputStreams als erstes aufgerufen.
     * Sollte konstant sein.
     *
     * @return Laenge des Headers
     */
    int getHeaderLength();


    /**
     * Hibt die Lange des Bodies des Packets auf Basis eines headers zurueck
     * Sollte bei gleichem Header das gleiche Ergebnis zurueckgeben
     *
     * @param header Inhalt des Headers waehrend der Initialisierung eines Packets auf Basis eines InputStreams
     * @return die Laenge des Bodies
     */
    int getBodyLength(byte[] header);

    /**
     * Gibt den Inhalt des Fields nach der Initialisierung zurueck
     * @return der Inhalt
     */
    T getContent();

    /**
     * Initialisiert das Field auf Basis seines Inhalts
     * @param content der Inhalt
     */
    void setContent(T content);

    /**
     * initialisiert das Field auf basis des bodies im ByteInputStream
     * @param body der, in bytes encodierte Inhalt des Fields. Die Laenge des Arrays ist getBodyLength().
     */
    void setContent(byte[] body);
}
