package de.gyko.gameofcolors.net;

/**
 * Ein einfaches Netzwerk-Packet
 *
 * @author anonymous123-code
 */
public abstract class Packet {
    /**
     * Der rohe Inhalt des Packets
     */
    protected byte[] rawContent;
    /**
     * Die id des Packets
     */
    protected String id;

    /**
     * Erzeugt ein Packet auf Basis des rohen Inhalts.
     * @param content der rohe Inhalt
     */
    public Packet(byte... content) {
        if(content.length < 3){
            throw new IllegalArgumentException("Too little bytes");
        }
        rawContent = content;
        id = new String(new byte[]{content[0], content[1], content[2]});
    }

    /**
     * Erzeugt ein leeres Packet.
     */
    public Packet() {
    }

    /**
     * Gibt den rohen Inhalt zurueck.
     *
     * @return der rohe Inhalt
     */
    public byte[] getRawContent() {
        return rawContent;
    }

    /**
     * Gibt die PacketId zurueck.
     *
     * @return die Id des Packets
     */
    public String getId(){
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
 id       sizeX(in bytes)  X   sizeY  Y   color
 64 72 77 00 01            02  00 01  05  ff 00 00
 drw      1                2   1      5   #ff0000

 player win
 id          nameLength name         color
 77 69 6e    00 04      4f 74 74 6f  ff 00 00
 win         4          Otto         #ff0000


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