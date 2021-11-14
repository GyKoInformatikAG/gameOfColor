package de.gyko.netLib.packet.field;

public class LongField implements Field<Long> {
    private Long content;

    @Override
    public int getHeaderLength() {
        return 0;
    }

    @Override
    public int getBodyLength(byte[] header) {
        return Long.BYTES;
    }

    @Override
    public Long getContent() {
        return this.content;
    }

    @Override
    public void setContent(Long content) {
        this.content = content;
    }

    @Override
    public void setContent(byte[] body) {
        long result = 0;
        for (int i = 0; i < Long.BYTES; i++) {
            result <<= Byte.SIZE;
            result |= (body[i] & 0xFF);
        }
        this.content = result;
    }

    @Override
    public byte[] getHeader() {
        return new byte[0];
    }

    @Override
    public byte[] getBody() {
        long cache = this.content;
        byte[] result = new byte[Long.BYTES];
        for (int i = Long.BYTES - 1; i >= 0; i--) {
            result[i] = (byte)(cache & 0xFF);
            cache >>= Byte.SIZE;
        }
        return result;
    }
}
