package com.stephen.minigame.instance;

import com.stephen.minigame.GameState;
import com.stephen.minigame.Minigame;
import com.stephen.minigame.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    private Minigame minigame;
    private Arena arena;
    private int seconds;

    public Countdown(Minigame minigame, Arena arena) {
        this.minigame = minigame;
        this.arena = arena;
        this.seconds = ConfigManager.getCountdownSeconds();
    }

    public void start() {
        arena.setState(GameState.COUNTDOWN);
        runTaskTimer(minigame, 0, 20);
    }

    @Override
    public void run() {
        if (seconds == 0) {
            cancel();
            arena.start();
            arena.sendTitle("","");
            return;
        }

        if (arena.getPlayers().size() < ConfigManager.getRequiredPlayers()) {
            arena.reset();
            arena.sendMessage(ChatColor.RED + "There is not enough players. Countdown stopped.");
            arena.sendTitle("","");
            return;
        }

        if (seconds % 30 == 0 || seconds <= 10) {
            arena.sendMessage(ChatColor.GREEN + "Game will start in " + seconds + " second" + (seconds == 1 ? "" : "s") + ".");
        }

        arena.sendTitle(ChatColor.GREEN.toString() + seconds + " second" + (seconds == 1 ? "" : "s"), ChatColor.GRAY + "until game starts");

        seconds--;
    }

}