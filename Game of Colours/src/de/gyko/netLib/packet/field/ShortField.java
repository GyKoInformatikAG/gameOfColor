package de.gyko.netLib.packet.field;

/**
 * Enthaelt einen Short
 *
 * @author anonymous123-code
 */
public class ShortField implements Field<Short>{
    short content;

    @Override
    public int getHeaderLength() {
        return 0;
    }

    @Override
    public int getBodyLength(byte[] header) {
        return Short.BYTES;
    }

    @Override
    public Short getContent() {
        return this.content;
    }

    @Override
    public void setContent(Short content) {
        this.content = content;
    }

    @Override
    public void setContent(byte[] body) {
        this.content = (short) (
                ((body[2] & 0xFF) << 8 ) |
                ((body[3] & 0xFF))
        );
    }

    @Override
    public byte[] getHeader() {
        return new byte[0];
    }

    @Override
    public byte[] getBody() {
        return new byte[]{
                (byte)((this.content >> 8) & 0xFF),
                (byte)(this.content & 0xFF)
        };
    }
}
