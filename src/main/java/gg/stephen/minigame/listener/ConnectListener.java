package gg.stephen.minigame.listener;

import gg.stephen.minigame.Minigame;
import gg.stephen.minigame.manager.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectListener implements Listener {

    private Minigame minigame;

    public ConnectListener(Minigame minigame) {
        this.minigame = minigame;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        e.getPlayer().teleport(ConfigManager.getLobbySpawn());

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        Player player = e.getPlayer();

        if (minigame.getArenaManager().isPlaying(player)) {
            minigame.getArenaManager().getArena(player).removePlayer(player);
        }

    }

}
