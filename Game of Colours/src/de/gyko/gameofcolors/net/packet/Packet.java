package de.gyko.gameofcolors.net.packet;


import de.gyko.gameofcolors.net.packet.field.Field;

/**
 * Ein einfaches Netzwerk-Packet
 *
 * @author anonymous123-code
 */
public abstract class Packet {
    /**
     * Die id des Packets
     */
    protected String id;

    /**
     * Erzeugt ein leeres Packet.
     */
    public Packet() {}

    /**
     * Gibt alle Felder im Packet zurueck.
     * Sollte sich nicht veraendern
     * @return Eine Liste mit allen Fields
     */
    public Field[] getFields() {return new Field[0];}

    /**
     * Gibt die PacketId zurueck.
     *
     * @return die Id des Packets
     */
    public String getId() {
        return id;
    }
}
/*
   server -> client


 player leave
 id        color
 70 6c 6c  ff 00 00
 pll       #ff0000

 pixel drawn
 id        X              Y               color
 64 72 77  00 00 00 02    00 00 00 05     ff 00 00
 drw       2              5               #ff0000

 player win
 id          nameLength       name           color
 77 69 6e    00 00 00 04      4f 74 74 6f    ff 00 00
 win         4                Otto           #ff0000


   client ->  server


 player leave
 id
 70 6c 6c
 pll

 draw pixel
 id       sizeX(in bytes)  X   sizeY  Y
 64 72 77 00 01            02  00 01  05
 drw      1                2   1      5
*/



/* FERTIG
   server -> client

 player join
 id        nameLength name         color
 70 6c 6a  04         4f 74 74 6f  ff 00 00
 plj       4          Otto         #ff0000


   client -> server

 player join
 id        nameLength name         color
 70 6c 6a  00 04      4f 74 74 6f  ff 00 00
 plj       4          Otto         #ff0000

 */