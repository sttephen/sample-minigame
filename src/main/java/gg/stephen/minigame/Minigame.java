package gg.stephen.minigame;

import gg.stephen.minigame.command.ArenaCommand;
import gg.stephen.minigame.listener.ConnectListener;
import gg.stephen.minigame.listener.GameListener;
import gg.stephen.minigame.manager.ArenaManager;
import gg.stephen.minigame.manager.ConfigManager;
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
