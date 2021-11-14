package de.gyko.netLib.packet.field;

import java.nio.ByteBuffer;

/**
 * Enthaelt einen double
 *
 * @author anonymous123-code
 */
public class DoubleField implements Field<Double>{
    Double content;

    @Override
    public int getHeaderLength() {
        return 0;
    }

    @Override
    public int getBodyLength(byte[] header) {
        return Double.BYTES;
    }

    @Override
    public Double getContent() {
        return this.content;
    }

    @Override
    public void setContent(Double content) {
        this.content = content;
    }

    @Override
    public void setContent(byte[] body) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Double.BYTES);
        byteBuffer.put(body);
        byteBuffer.flip();
        content = byteBuffer.getDouble();
    }

    @Override
    public byte[] getHeader() {
        return new byte[0];
    }

    @Override
    public byte[] getBody() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Double.BYTES);
        byteBuffer.putDouble(content);
        return byteBuffer.array();
    }
}
