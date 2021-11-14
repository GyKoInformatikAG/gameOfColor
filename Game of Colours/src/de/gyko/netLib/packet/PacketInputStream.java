package de.gyko.netLib.packet;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Ein Stream, in dem Packets gelesen werden
 *
 * @author anonymous123-code
 */
public class PacketInputStream implements Iterator<Packet>, Closeable {
    private boolean closed = false;
    private final InputStream input;
    private final PacketFactory factory;
    private byte[] next;

    public PacketInputStream(InputStream stream, PacketFactory factory){
        this.input = stream;
        this.factory = factory;
    }

    @Override
    public void close() throws IOException {
        if(!closed) input.close();
        closed = true;
    }

    @Override
    public boolean hasNext() {
        return input;
    }

    private void fillNext(){
        try {
            byte[] idEncoded = new byte[3];
            //noinspection ResultOfMethodCallIgnored
            input.read(idEncoded, 0, 3);
            String id = new String(idEncoded, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Packet next() {
        return null;
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }

    @Override
    public void forEachRemaining(Consumer<? super Packet> action) {
        Iterator.super.forEachRemaining(action);
    }
}
