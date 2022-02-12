package de.gyko.netLib.packet.field;

import java.lang.reflect.Array;

public class ArrayField<F extends Field<B>, B> implements Field<F[]>{
    private F[] content;
    private final Class<F> contentClass;

    public ArrayField(Class<F> contentClass) {
        this.contentClass = contentClass;
    }

    @Override
    public int getHeaderLength() {
        return 1;
    }

    @Override
    public int getBodyLength(byte[] header) {
        return header[0];
    }

    @Override
    public F[] getContent() {
        return content;
    }

    @Override
    public void setContent(F[] content) {
        this.content = content;
    }

    @Override
    public void setContent(byte[] body) {
        if (body.length == 0) this.content = (F[]) Array.newInstance(contentClass, 0);
        int length = body[0];
        F[] arr = (F[]) Array.newInstance(contentClass, length);
        int pos = 1;
        int element = 0;
        while (pos < body.length && element < length) {
            try {
                arr[element] = contentClass.newInstance();
                int headerLength = getHeaderLength();
                byte[] header = new byte[headerLength];
                System.arraycopy(body, pos, header, 0, headerLength);
                pos += headerLength;
                int bodyLength = arr[element].getBodyLength(header);
                byte[] bodyF = new byte[bodyLength];
                System.arraycopy(body, pos, bodyF, 0, bodyLength);
                pos += bodyLength;
                arr[element].setContent(bodyF);
                element++;
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        this.content = arr;
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
