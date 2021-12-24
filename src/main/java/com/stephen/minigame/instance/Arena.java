package com.stephen.minigame.instance;

import com.stephen.minigame.GameState;
import com.stephen.minigame.Minigame;
import com.stephen.minigame.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

    private Minigame minigame;

    private int id;
    private Location spawn;

    private GameState state;
    private ArrayList<UUID> players;
    private Countdown countdown;
    private Game game;

    public Arena(Minigame minigame, int id, Location spawn) {
        this.minigame = minigame;

        this.id = id;
        this.spawn = spawn;

        this.state = GameState.RECRUITING;
        this.players = new ArrayList<>();
        this.countdown = new Countdown(minigame, this);
        this.game = new Game(this);
    }

    /* GAME */
    public void start() {
        game.start();
    }

    public void reset() {
        for (UUID uuid : players) {
            Player player = Bukkit.getPlayer(uuid);
            players.remove(player.getUniqueId());
            player.teleport(ConfigManager.getLobbySpawn());
            player.sendTitle("","");
        }
        countdown.cancel();
        state = GameState.RECRUITING;
        this.countdown = new Countdown(minigame, this);
        this.game = new Game(this);
    }

    /* PLAYERS */

    public void addPlayer(Player player) {
        players.add(player.getUniqueId());
        player.teleport(spawn);

        if (state.equals(GameState.RECRUITING) && players.size() >= ConfigManager.getRequiredPlayers()) {
            countdown.start();
        }
    }

    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
        player.teleport(ConfigManager.getLobbySpawn());
        player.sendTitle("","");

        if (state == GameState.LIVE && players.size() == 0) {
            reset();
        }
    }

    /* TOOLS */

    public void sendMessage(String message) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendMessage(message);
        }
    }

    public void sendTitle(String title, String subTitle) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendTitle(title, subTitle);
        }
    }

    public int getId() { return id; }

    public List<UUID> getPlayers() { return players; }
    public GameState getState() { return state; }
    public Game getGame() { return game; }

    public void setState(GameState state) { this.state = state; }

}
