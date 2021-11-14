package de.gyko.netLib.packet.field;

import java.nio.charset.StandardCharsets;

public class StringField implements Field<String> {
    String content;
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
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void setContent(byte[] body) {
        this.content = new String(body, StandardCharsets.UTF_8);
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
