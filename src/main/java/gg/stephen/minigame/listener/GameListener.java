package gg.stephen.minigame.listener;

import gg.stephen.minigame.GameState;
import gg.stephen.minigame.Minigame;
import gg.stephen.minigame.instance.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class GameListener implements Listener {

    private Minigame minigame;

    public GameListener(Minigame minigame) {
        this.minigame = minigame;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        Player player = e.getPlayer();

        if (minigame.getArenaManager().isPlaying(player)) {
            Arena arena = minigame.getArenaManager().getArena(player);
            if (arena.getState().equals(GameState.LIVE)) {
                arena.getGame().addPoint(player);
            }
        }

    }

}
