package com.arturoo404.minecraftplugin.listener;

import com.arturoo404.minecraftplugin.service.MinecraftPlayerStatus;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class PlayerServerEvent implements Listener {

    private final MinecraftPlayerStatus minecraftPlayerStatus = new MinecraftPlayerStatus();

    @EventHandler
    public void joinServer(PlayerJoinEvent event) throws SQLException {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.AQUA + "Welcome!");
        minecraftPlayerStatus.playerOnline(true, player.getDisplayName(), player.getUniqueId().toString());
    }

    @EventHandler
    public void quitServer(PlayerQuitEvent event) throws SQLException {
        Player player = event.getPlayer();
        minecraftPlayerStatus.playerOnline(false, player.getDisplayName(), player.getUniqueId().toString());
    }
}
