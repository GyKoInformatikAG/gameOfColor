package de.gyko.gameofcolors.net;

import java.nio.charset.StandardCharsets;

/*
 * A simple Text packet
 *
 * @author anonymous123-code
 */
public class TextPacket extends Packet{
    public TextPacket(String s) {
        super();
        // TODO: aktuell nur fuer Testzwecke, richtig machen
        byte[] rawContent = new byte[3+s.getBytes(StandardCharsets.UTF_8).length];
        rawContent[0] = "txt".getBytes(StandardCharsets.UTF_8)[0];
        rawContent[1] = "txt".getBytes(StandardCharsets.UTF_8)[1];
        rawContent[2] = "txt".getBytes(StandardCharsets.UTF_8)[2];
        byte[] sBytes = s.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(sBytes, 0, rawContent, 3, sBytes.length + 3 - 3);
        this.rawContent = rawContent;
        id = new String(new byte[]{rawContent[0], rawContent[1], rawContent[2]});
    }

    @Override
    public byte[] getRawContent() {
        // TODO: Workaround für Testzwecke, Später entfernen
        byte[] out = new byte[this.rawContent.length-3];
        System.arraycopy(this.rawContent, 3, out, 0, this.rawContent.length-3);
        return out;
    }
}
