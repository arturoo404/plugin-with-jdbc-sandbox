package com.arturoo404.minecraftplugin.player;

import com.arturoo404.minecraftplugin.service.impl.MinecraftPlayerService;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class PlayerServerEvent implements Listener {

    private final MinecraftPlayerService minecraftPlayerService = new MinecraftPlayerService();

    @EventHandler
    public void joinServer(PlayerJoinEvent event) throws SQLException {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.AQUA + "Welcome!");
        minecraftPlayerService.playerOnline(true, player.getDisplayName(), player.getUniqueId().toString());
    }

    @EventHandler
    public void quitServer(PlayerQuitEvent event) throws SQLException {
        Player player = event.getPlayer();
        minecraftPlayerService.playerOnline(false, player.getDisplayName(), player.getUniqueId().toString());
    }
}
