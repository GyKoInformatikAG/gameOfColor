package de.gyko.gameofcolors.net.packet.field;

public class Integer implements Field<java.lang.Integer> {
    private java.lang.Integer content;

    @Override
    public int getHeaderLength() {
        return 0;
    }

    @Override
    public int getBodyLength(byte[] header) {
        return 4;
    }

    @Override
    public java.lang.Integer getContent() {
        return this.content;
    }

    @Override
    public void setContent(java.lang.Integer content) {
        this.content = content;
    }

    @Override
    public void setContent(byte[] body) {
        this.content = ((body[0] & 0xFF) << 24) |
                ((body[1] & 0xFF) << 16) |
                ((body[2] & 0xFF) << 8 ) |
                ((body[3] & 0xFF));
    }

    @Override
    public byte[] getHeader() {
        return new byte[0];
    }

    @Override
    public byte[] getBody() {
        return new byte[] {
                (byte)(content >> 24),
                (byte)(content >> 16),
                (byte)(content >> 8),
                (byte)content.intValue() };
    }
}
