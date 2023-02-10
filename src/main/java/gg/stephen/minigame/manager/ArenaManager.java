package gg.stephen.minigame.manager;

import gg.stephen.minigame.Minigame;
import gg.stephen.minigame.instance.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {

    private List<Arena> arenas = new ArrayList<>();

    public ArenaManager(Minigame minigame) {
        FileConfiguration config = minigame.getConfig();
        for (String str : config.getConfigurationSection("arenas.").getKeys(false)) {
            int id = Integer.parseInt(str);
            arenas.add(new Arena(minigame, id,
                    new Location(Bukkit.getWorld(config.getString("arenas." + id + ".world")),
                    config.getDouble("arenas." + id + ".x"),
                    config.getDouble("arenas." + id + ".y"),
                    config.getDouble("arenas." + id + ".z"),
                    (float) config.getDouble("arenas." + id + ".yaw"),
                    (float) config.getDouble("arenas." + id + ".pitch"))));
        }
    }

    public List<Arena> getArenas() { return arenas; }

    public boolean isPlaying(Player player) { return getArena(player) != null; }

    public Arena getArena(Player player) {
        for (Arena arena : arenas) {
            if (arena.getPlayers().contains(player.getUniqueId())) {
                return arena;
            }
        }
        return null;
    }

    public Arena getArena(int id) {
        for (Arena arena : arenas) {
            if (arena.getId() == id) {
                return arena;
            }
        }
        return null;
    }

}
