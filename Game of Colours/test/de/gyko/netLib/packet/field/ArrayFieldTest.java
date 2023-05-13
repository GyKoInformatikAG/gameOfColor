package de.gyko.netLib.packet.field;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class ArrayFieldTest {
    @Test
    public void fromBooleanArray() {
        arrayFieldTests(
                () -> new ArrayField<>((length, instanciate) -> {
                    BooleanField[] result = new BooleanField[length];
                    if (instanciate) {
                        for (int i = 0; i < length; i++) {
                            result[i] = new BooleanField();
                        }
                    }
                    return result;
                }, Boolean[]::new),
                new Boolean[]{true, false, true},
                new byte[]{0, 0, 0, 4},
                new byte[]{3, 1, 0, 1}
        );
    }

    @Test
    public void fromIntArray() {
        arrayFieldTests(
                () -> new ArrayField<>((length, instanciate) -> {
                    IntegerField[] result = new IntegerField[length];
                    if (instanciate) {
                        for (int i = 0; i < length; i++) {
                            result[i] = new IntegerField();
                        }
                    }
                    return result;
                }, Integer[]::new),
                new Integer[]{1, 2, 3, 257},
                new byte[]{0, 0, 0, 17},
                new byte[]{4, 0, 0, 0, 1, 0, 0, 0, 2, 0, 0, 0, 3, 0, 0, 1, 1}
        );
    }

    @Test
    public void fromStringArray() {
        arrayFieldTests(() -> new ArrayField<>((length, instanciate) -> {
            IntegerField[] result = new IntegerField[length];
            if (instanciate) {
                for (int i = 0; i < length; i++) {
                    result[i] = new IntegerField();
                }
            }
            return result;
        }, Integer[]::new),
                new Integer[]{1, 2, 3, 257},
                new byte[]{0, 0, 0, 17},
                new byte[]{4, 0, 0, 0, 1, 0, 0, 0, 2, 0, 0, 0, 3, 0,0, 1, 1}
        );
    }

    private static <FieldType extends Field<FieldInnerType>, FieldInnerType> void arrayFieldTests(Supplier<ArrayField<FieldType, FieldInnerType>> fieldSupplier, FieldInnerType[] content, byte[] expectedHeader, byte[] expectedBody) {
        var arrayField = fieldSupplier.get();
        arrayField.setContent(content);
        assertArrayEquals(content, arrayField.getContent());

        var header = arrayField.getHeader();
        var body = arrayField.getBody();

        assertArrayEquals(expectedHeader, header);
        assertEquals(expectedBody.length, arrayField.getBodyLength(header));
        assertArrayEquals(expectedBody, body);

        var parsedArrayField = fieldSupplier.get();
        parsedArrayField.setContent(body);
        assertArrayEquals(content, parsedArrayField.getContent());
    }
}