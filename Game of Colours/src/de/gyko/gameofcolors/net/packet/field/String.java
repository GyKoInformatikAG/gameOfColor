package de.gyko.gameofcolors.net.packet.field;

import java.nio.charset.StandardCharsets;

public class String implements Field<java.lang.String> {
    java.lang.String content;
    byte[] cachedBody;

    @Override
    public int getHeaderLength() {
        return 2;
    }

    @Override
    public int getBodyLength(byte[] header) {
        return ((header[0] & 0xFF) << 8 ) |
                ((header[1] & 0xFF));
    }

    @Override
    public java.lang.String getContent() {
        return content;
    }

    @Override
    public void setContent(java.lang.String content) {
        this.content = content;
    }

    @Override
    public void setContent(byte[] body) {
        this.content = new java.lang.String(body, StandardCharsets.UTF_8);
    }

    @Override
    public byte[] getHeader() {
        cachedBody = content.getBytes(StandardCharsets.UTF_8);
        int length = cachedBody.length;
        return new byte[]{
                (byte)(length >> 8),
                (byte)length
        };
    }

    @Override
    public byte[] getBody() {
        return cachedBody;
    }
}
