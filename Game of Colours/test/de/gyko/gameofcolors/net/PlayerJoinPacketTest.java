package de.gyko.gameofcolors.net;

import de.gyko.gameofcolors.Utility;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/*
 *
 *
 * @author anonymous123-code
 */
class PlayerJoinPacketTest {

    @Test
    void getPlayerColor() {
        assertEquals(new PlayerJoinPacket("Otto", new Color(255,255,255)).getPlayerColor(), new Color(255,255,255));
    }

    @Test
    void getPlayerName() {
        assertEquals(new PlayerJoinPacket("Otto", new Color(255,255,255)).getPlayerName(), "Otto");
    }

    @Test
    void constructorFromBytesWrongPacketId() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> new PlayerJoinPacket(Utility.toBytes(0x00, 0x01, 0x02)));
        assertTrue(e.getMessage().contains("wrong packetId"));
    }

    @Test
    void constructorFromBytesOnlyThreeBytes() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> new PlayerJoinPacket(Utility.toBytes(0x70, 0x6c, 0x6a)));
        assertTrue(e.getMessage().contains("too little bytes"));
    }

    @Test
    void constructorFromBytesNameLengthZero() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> new PlayerJoinPacket(Utility.toBytes(0x70, 0x6c, 0x6a, 0x00, 0x00, 0x00, 0x00)));
        assertTrue(e.getMessage().contains("nameLength must not be 0"));
    }

    @Test
    void constructorFromBytesTooMuchBytes() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> new PlayerJoinPacket(Utility.toBytes(0x70, 0x6c, 0x6a, 0x01, 0x6a, 0x00, 0x00, 0x00, 0x00)));
        assertTrue(e.getMessage().contains("too much bytes"));
    }

    @Test
    void constructorFromBytesTooLittleBytes() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> new PlayerJoinPacket(Utility.toBytes(0x70, 0x6c, 0x6a, 0x01, 0x6a, 0x00, 0x00)));
        assertTrue(e.getMessage().contains("too little bytes"));
    }

    @Test
    void constructorFromBytes() {
        PlayerJoinPacket playerJoinPacket1 = new PlayerJoinPacket(Utility.toBytes(0x70, 0x6c, 0x6a, 0x04, 0x4a, 0x61, 0x6e, 0x6f, 0x00, 0x00, 0x00));
        assertEquals("plj", playerJoinPacket1.getId());
        assertEquals("anonymous123-code", playerJoinPacket1.getPlayerName());
        assertEquals(new Color(0,0,0), playerJoinPacket1.getPlayerColor());

        playerJoinPacket1 = new PlayerJoinPacket(Utility.toBytes(0x70, 0x6c, 0x6a, 0x0f, 0x4d, 0x65, 0x69, 0x6e, 0x4e, 0x61, 0x6d, 0x65, 0x49, 0x73, 0x74, 0x4f, 0x74, 0x74, 0x6f, 0xff, 0x00, 0x00));
        assertEquals("plj", playerJoinPacket1.getId());
        assertEquals("MeinNameIstOtto", playerJoinPacket1.getPlayerName());
        assertEquals(new Color(0xff,0,0), playerJoinPacket1.getPlayerColor());

        playerJoinPacket1 = new PlayerJoinPacket(Utility.toBytes(0x70, 0x6c, 0x6a, 0x0f, 0x4d, 0x65, 0x69, 0x6e, 0x4e, 0x61, 0x6d, 0x65, 0x49, 0x73, 0x74, 0x4f, 0x74, 0x74, 0x6f, 0xff, 0xff, 0xff));
        assertEquals("plj", playerJoinPacket1.getId());
        assertEquals("MeinNameIstOtto", playerJoinPacket1.getPlayerName());
        assertEquals(new Color(0xff,0xff,0xff), playerJoinPacket1.getPlayerColor());
    }
}

