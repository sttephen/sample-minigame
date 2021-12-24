package com.stephen.minigame.instance;

import com.stephen.minigame.GameState;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Game {

    private Arena arena;
    private HashMap<UUID, Integer> points;

    public Game(Arena arena) {
        this.arena = arena;
        this.points = new HashMap<>();
    }

    public void start() {
        arena.setState(GameState.LIVE);
        arena.sendMessage(ChatColor.GREEN + "GAME HAS STARTED! Your objective is to be the first player to break 20 blocks. Good luck!");

        for (UUID uuid : arena.getPlayers()) {
            points.put(uuid, 0);
        }
    }

    public void addPoint(Player player) {
        int playerPoints = points.get(player.getUniqueId()) + 1;
        if (playerPoints == 20) {
            arena.sendMessage(ChatColor.GOLD + player.getName() + " HAS WON! Thanks for playing this minigame.");
            arena.reset(true);
            return;
        }

        player.sendMessage(ChatColor.GREEN + "+1 Point!");
        points.replace(player.getUniqueId(), playerPoints);
    }

}
