package de.gyko.netLib.Packet.field;

import java.awt.*;

public class ColorField implements Field<Color>{
    private Color content;

    @Override
    public int getHeaderLength() {
        return 0;
    }

    @Override
    public int getBodyLength(byte[] header) {
        return 3;
    }

    @Override
    public Color getContent() {
        return this.content;
    }

    @Override
    public void setContent(Color content) {
        this.content = content;
    }

    @Override
    public void setContent(byte[] body) {
        this.content = new Color(Byte.toUnsignedInt(body[0]), Byte.toUnsignedInt(body[1]), Byte.toUnsignedInt(body[2]));
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
