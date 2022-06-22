package com.arturoo404.minecraftplugin.listener;

import com.arturoo404.minecraftplugin.MinecraftPlugin;
import com.arturoo404.minecraftplugin.service.MinecraftPlayerStatus;
import com.arturoo404.minecraftplugin.service.Scoreboard;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;

public class PlayerServerEvent implements Listener {

    private final MinecraftPlugin minecraftPlugin;
    private final MinecraftPlayerStatus minecraftPlayerStatus = new MinecraftPlayerStatus();
    private final Scoreboard board = new Scoreboard();

    public PlayerServerEvent(MinecraftPlugin minecraftPlugin) {
        this.minecraftPlugin = minecraftPlugin;
    }

    @EventHandler
    public void joinServer(PlayerJoinEvent event) throws SQLException {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.AQUA + "Welcome!");
        minecraftPlayerStatus.playerOnline(true, player.getDisplayName(), player.getUniqueId().toString());
        new BukkitRunnable(){
            @Override
            public void run() {
                try {
                    board.scoreBoard(player);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }.runTaskTimer(minecraftPlugin, 0, 20);
    }

    @EventHandler
    public void quitServer(PlayerQuitEvent event) throws SQLException {
        Player player = event.getPlayer();
        minecraftPlayerStatus.playerOnline(false, player.getDisplayName(), player.getUniqueId().toString());
    }
}
