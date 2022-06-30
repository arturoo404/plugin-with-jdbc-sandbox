package com.arturoo404.minecraftplugin.service;

import com.arturoo404.minecraftplugin.MinecraftPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.Bukkit.getServer;

public class HealthBar {

    private final MinecraftPlugin plugin;

    public HealthBar(MinecraftPlugin plugin) {
        this.plugin = plugin;
    }

    public void entityHp(){
        new BukkitRunnable(){

            @Override
            public void run() {
                for (LivingEntity living : getServer().getWorld("world").getLivingEntities()){
                    living.setCustomName(ChatColor.WHITE + living.getType().name() +": " + ChatColor.RED + (int) living.getHealth());
                    living.setCustomNameVisible(true);
                }
            }
        }.runTaskTimer(plugin, 0, 5);
    }
}
