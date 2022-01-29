package de.gyko.gameofcolors.net.event;

import java.awt.*;

public class PlayerJoinEvent {
    public final Color color;
    public final String name;

    public PlayerJoinEvent(Color color, String name)  {
        this.color = color;
        this.name = name;
    }
}
