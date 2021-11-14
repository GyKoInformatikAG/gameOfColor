package de.gyko.netLib.packet;

import de.gyko.netLib.packet.field.Field;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * Ein Stream, in dem Packets gelesen werden
 *
 * @author anonymous123-code
 */
public class PacketInputStream extends FilterInputStream implements Iterator<Packet> {
    private final PacketFactory factory;
    private boolean hasNext = true;


    public PacketInputStream(InputStream stream, PacketFactory factory){
        super(stream);
        this.factory = factory;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    private Packet getNext() {
        try {
            byte[] idEncoded = new byte[3];
            //noinspection ResultOfMethodCallIgnored
            in.read(idEncoded, 0, 3);
            String id = new String(idEncoded, StandardCharsets.UTF_8);
            Packet p = factory.getPacket(id);
            byte[] header;
            byte[] body;
            for (@SuppressWarnings("rawtypes") Field field : p.getFields()) {
                header = new byte[field.getHeaderLength()];
                //noinspection ResultOfMethodCallIgnored
                in.read(header, 0, field.getHeaderLength());
                body = new byte[field.getBodyLength(header)];
                //noinspection ResultOfMethodCallIgnored
                in.read(body, 0, field.getBodyLength(header));
                field.setContent(body);
            }
            return p;
        } catch (IOException e) {
            hasNext = false;
            try {
                if(e.getMessage().equalsIgnoreCase("Connection reset")) this.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Packet next() {
        return getNext();
    }
}
