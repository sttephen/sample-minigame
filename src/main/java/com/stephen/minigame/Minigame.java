package com.stephen.minigame;

import com.stephen.minigame.command.ArenaCommand;
import com.stephen.minigame.instance.Arena;
import com.stephen.minigame.instance.Game;
import com.stephen.minigame.listener.ConnectListener;
import com.stephen.minigame.listener.GameListener;
import com.stephen.minigame.manager.ArenaManager;
import com.stephen.minigame.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Minigame extends JavaPlugin {

    private ArenaManager arenaManager;

    @Override
    public void onEnable() {
        ConfigManager.setupConfig(this);

        arenaManager = new ArenaManager(this);

        Bukkit.getPluginManager().registerEvents(new ConnectListener(this), this);
        Bukkit.getPluginManager().registerEvents(new GameListener(this), this);

        getCommand("arena").setExecutor(new ArenaCommand(this));
    }

    public ArenaManager getArenaManager() { return arenaManager; }
}
