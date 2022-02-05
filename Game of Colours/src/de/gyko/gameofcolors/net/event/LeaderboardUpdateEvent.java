package de.gyko.gameofcolors.net.event;

import java.awt.*;

public class LeaderboardUpdateEvent {
    public final Color[] leaderboard;

    public LeaderboardUpdateEvent(Color[] leaderboard) {
        this.leaderboard = leaderboard;
    }
}
