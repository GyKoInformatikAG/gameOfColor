package de.gyko.netLib.packet.field;

/**
 * Enthaelt einen Wahrheitswert.
 *
 * @author anonymous123-code
 */
public class BooleanField implements Field<Boolean> {
    private boolean content;

    @Override
    public int getHeaderLength() {
        return 0;
    }

    @Override
    public int getBodyLength(byte[] header) {
        return 1;
    }

    @Override
    public Boolean getContent() {
        return this.content;
    }

    @Override
    public void setContent(Boolean content) {
        this.content = content;
    }

    @Override
    public void setContent(byte[] body) {
        this.content = 0 != body[0];
    }

    @Override
    public byte[] getHeader() {
        return new byte[0];
    }

    @Override
    public byte[] getBody() {
        return new byte[]{(byte) (this.content?0x01:0x00)};
    }
}
