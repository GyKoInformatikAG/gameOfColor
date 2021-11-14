package de.gyko.netLib.packet.field;

/**
 * Enthaelt ein Byte
 *
 * @author anonymous123-code
 */
public class ByteField implements Field<Byte>{
    byte content;

    @Override
    public int getHeaderLength() {
        return 0;
    }

    @Override
    public int getBodyLength(byte[] header) {
        return 1;
    }

    @Override
    public Byte getContent() {
        return this.content;
    }

    @Override
    public void setContent(Byte content) {
        this.content = content;
    }

    @Override
    public void setContent(byte[] body) {
        this.content = body[0];
    }

    @Override
    public byte[] getHeader() {
        return new byte[0];
    }

    @Override
    public byte[] getBody() {
        return new byte[]{content};
    }
}
