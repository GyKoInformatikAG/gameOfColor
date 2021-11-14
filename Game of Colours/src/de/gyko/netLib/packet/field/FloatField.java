package de.gyko.netLib.packet.field;

import java.nio.ByteBuffer;

/**
 * Enthaelt einen float
 *
 * @author anonymous123-code
 */
public class FloatField implements Field<Float>{
    Float content;

    @Override
    public int getHeaderLength() {
        return 0;
    }

    @Override
    public int getBodyLength(byte[] header) {
        return Float.BYTES;
    }

    @Override
    public Float getContent() {
        return this.content;
    }

    @Override
    public void setContent(Float content) {
        this.content = content;
    }

    @Override
    public void setContent(byte[] body) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Float.BYTES);
        byteBuffer.put(body);
        byteBuffer.flip();
        content = byteBuffer.getFloat();
    }

    @Override
    public byte[] getHeader() {
        return new byte[0];
    }

    @Override
    public byte[] getBody() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Float.BYTES);
        byteBuffer.putFloat(content);
        return byteBuffer.array();
    }
}
