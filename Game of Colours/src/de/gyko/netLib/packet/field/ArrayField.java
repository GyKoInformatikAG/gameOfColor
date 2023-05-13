package de.gyko.netLib.packet.field;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * An array of fields
 * @param <FieldType> the Field type
 * @param <FieldInnerType> the type of the Field inside
 */
public class ArrayField<FieldType extends Field<FieldInnerType>, FieldInnerType> implements Field<FieldInnerType[]>{
    private FieldType[] content;
    private final BiFunction<Integer, Boolean, FieldType[]> arrayGenerator;
    private final Function<Integer, FieldInnerType[]> innerArrayGenerator;
    private boolean cacheValid = false;
    private byte[] cache;

    /**
     * @param arrayGenerator a function that generates a new array of the given length, and fills it with FieldType instances when second parameter is true
     * @param innerArrayGenerator a function that generates a new instance of the inner type
     */
    public ArrayField(BiFunction<Integer, Boolean, FieldType[]> arrayGenerator, Function<Integer, FieldInnerType[]> innerArrayGenerator) {
        this.arrayGenerator = arrayGenerator;
        this.innerArrayGenerator = innerArrayGenerator;
    }

    @Override
    public int getHeaderLength() {
        return 4;
    }

    @Override
    public int getBodyLength(byte[] header) {
        return ((header[0] & 0xFF) << 24 ) |
                ((header[1] & 0xFF) << 16 ) |
                ((header[2] & 0xFF) << 8 ) |
                ((header[3] & 0xFF));
    }

    @Override
    public FieldInnerType[] getContent() {
        return Arrays.stream(this.content)
                .map(Field::getContent)
                .toArray(innerArrayGenerator::apply);
    }

    @Override
    public void setContent(FieldInnerType[] content) {
        this.content = this.arrayGenerator.apply(content.length, true);
        for (int i = 0; i < content.length; i++) {
            this.content[i].setContent(content[i]);
        }
        this.cacheValid = false;
    }

    public void setContent(FieldType[] content) {
        this.content = content;
        this.cacheValid = false;
    }

    @Override
    public void setContent(byte[] body) {
        if (body.length == 0) this.content = this.arrayGenerator.apply(0, false);
        int length = body[0];
        FieldType[] arr = this.arrayGenerator.apply(length, true);
        int pos = 1;
        int element = 0;
        while (pos < body.length && element < length) {
            int headerLength = arr[element].getHeaderLength();
            byte[] header = new byte[headerLength];
            System.arraycopy(body, pos, header, 0, headerLength);
            pos += headerLength;
            int bodyLength = arr[element].getBodyLength(header);
            byte[] bodyF = new byte[bodyLength];
            System.arraycopy(body, pos, bodyF, 0, bodyLength);
            pos += bodyLength;
            arr[element].setContent(bodyF);
            element++;
        }
        this.content = arr;
        this.cache = body;
        this.cacheValid = true;
    }

    private void updateCache() {
        if (cacheValid) return;
        int length = 1;
        for (FieldType field : content) {
            length += field.getHeaderLength() + field.getBodyLength(field.getHeader());
        }
        byte[] cache = new byte[length];
        cache[0] = (byte) content.length;
        int pos = 1;
        for (FieldType field : content) {
            System.arraycopy(field.getHeader(), 0, cache, pos, field.getHeaderLength());
            pos += field.getHeaderLength();
            System.arraycopy(field.getBody(), 0, cache, pos, field.getBodyLength(field.getHeader()));
            pos += field.getBodyLength(field.getHeader());
        }
        this.cache = cache;
        this.cacheValid = true;
    }

    @Override
    public byte[] getHeader() {
        updateCache();
        return new byte[] { (byte) ((cache.length >> 24) & 0xFF),
                (byte) ((cache.length >> 16) & 0xFF),
                (byte) ((cache.length >> 8) & 0xFF),
                (byte) (cache.length & 0xFF) };
    }

    @Override
    public byte[] getBody() {
        updateCache();
        return cache;
    }
}
