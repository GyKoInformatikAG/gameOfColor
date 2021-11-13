package de.gyko.gameofcolors.net.packet.field;

public class Color implements Field<java.awt.Color>{
    private java.awt.Color content;

    @Override
    public int getHeaderLength() {
        return 0;
    }

    @Override
    public int getBodyLength(byte[] header) {
        return 3;
    }

    @Override
    public java.awt.Color getContent() {
        return this.content;
    }

    @Override
    public void setContent(java.awt.Color content) {
        this.content = content;
    }

    @Override
    public void setContent(byte[] body) {
        this.content = new java.awt.Color(Byte.toUnsignedInt(body[0]), Byte.toUnsignedInt(body[1]), Byte.toUnsignedInt(body[2]));
    }

    @Override
    public byte[] getHeader() {
        return new byte[0];
    }

    @Override
    public byte[] getBody() {
        return new byte[0];
    }
}
