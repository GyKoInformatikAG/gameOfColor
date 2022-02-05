package de.gyko.gameofcolors.net;

import de.gyko.gameofcolors.net.event.*;
import de.gyko.netLib.PacketSendRequest;

import java.util.ArrayList;

public interface ClientListener {
    ArrayList<PacketSendRequest> onPlayerJoin(PlayerJoinEvent event);

    ArrayList<PacketSendRequest> onClick(ClickEvent event);

    ArrayList<PacketSendRequest> onLeaderboardUpdate(LeaderboardUpdateEvent event);

    ArrayList<PacketSendRequest> onGameStart(GameStartEvent event);

    ArrayList<PacketSendRequest> onGameEnd(GameEndEvent event);
}
