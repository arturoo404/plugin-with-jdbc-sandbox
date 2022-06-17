package com.arturoo404.minecraftplugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
public class DropItemTeleport implements Listener {

    @EventHandler
    public void dropItemTp(BlockBreakEvent event){
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (block.getType() == Material.DIRT){
            player.giveExp(100);
            player.sendMessage(ChatColor.AQUA + "Your exp: " + player.getExp());
        }
    }
}
