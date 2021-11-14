package de.gyko.netLib.packet;

import de.gyko.netLib.packet.field.Field;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Ein Stream, in den Packets ausgegeben werden
 *
 * @author anonymous123-code
 */
public class PacketOutputStream extends FilterOutputStream {
    private final PacketFactory factory;

    public PacketOutputStream(OutputStream out, PacketFactory factory) {
        super(out);
        this.factory = factory;
    }

    public void write(Packet packet) throws IOException {
        if(!factory.hasPacket(packet.getId())) throw new IllegalArgumentException("Packet Id not included in factory");
        out.write(packet.getId().getBytes(StandardCharsets.UTF_8));
        for (@SuppressWarnings("rawtypes") Field f : packet.getFields()) {
            out.write(f.getHeader());
            out.write(f.getBody());
        }
        out.flush();
    }
}
