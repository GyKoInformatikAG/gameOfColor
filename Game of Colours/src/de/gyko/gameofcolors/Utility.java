package de.gyko.gameofcolors;

/*
 * Some Helper Methods
 *
 * @author anonymous123-code
 */
public class Utility {
    public static byte[] toBytes(short... shorts) {
        byte[] result = new byte[shorts.length];
        for (int i = 0; i < shorts.length; i++) {
            result[i] = (byte) shorts[i];
        }
        return result;
    }

    public static byte[] toBytes(int... ints) {
        byte[] result = new byte[ints.length];
        for (int i = 0; i < ints.length; i++) {
            result[i] = (byte) ints[i];
        }
        return result;
    }

    public static int uint(byte b) {
        return Byte.toUnsignedInt(b);
    }
}
