package de.gyko.gameofcolors.net.event;

import java.awt.*;

public class ClickEvent {
    public final int x;
    public final int y;
    public final Color color;

    public ClickEvent(Color color, int x, int y)  {
        this.color = color;
        this.x = x;
        this.y = y;
    }
}
