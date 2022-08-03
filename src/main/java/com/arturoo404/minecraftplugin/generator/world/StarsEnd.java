package com.arturoo404.minecraftplugin.generator.world;

import com.arturoo404.minecraftplugin.MinecraftPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class StarsEnd {

    private final int locX;
    private final int locY;
    private final int locZ;

    private MinecraftPlugin plugin;

    public StarsEnd(int locX, int locY, int locZ, MinecraftPlugin plugin) {
        this.locX = locX;
        this.locY = locY;
        this.locZ = locZ;
        this.plugin = plugin;
    }


    public void generateStarsEnd(){
        new BukkitRunnable(){

            @Override
            public void run() {
                for (int x = 0; x < 16; x++){
                    for (int z = 0; z < 16; z++){
                        Location loc = new Location(Bukkit.getWorld("world"), locX + x, locY, locZ + z);
                        if (!loc.getBlock().getBiome().equals(Biome.PLAINS)){
                            break;
                        }
                        replaceBlocks(loc);
                    }
                }
                cancel();
            }
        }.runTaskTimer(this.plugin, 0, 0);
    }

    //sand stone
    private void replaceBlocks(Location location){
        for (int y = 0; y < 20; y++){
                location.setY(this.locY + y);
                if (location.getBlock().getType().equals(Material.AIR) && y > 10){
                    break;
                }

                if (location.getBlock().getType().equals(Material.GRASS_BLOCK) ||
                        location.getBlock().getType().equals(Material.DIRT)) {
                    int random = new Random()
                            .ints(0, 7)
                            .findFirst()
                            .getAsInt();

                    switch (random){
                        case 0,1,2 -> {
                            location.getBlock().setType(Material.BLACKSTONE);
                        }
                        case 3,4,5 ->{
                            location.getBlock().setType(Material.OBSIDIAN);
                        }
                        case 6 ->{
                            location.getBlock().setType(Material.GILDED_BLACKSTONE);
                        }
                        default -> location.getBlock().setType(Material.AIR);
                    }
                }
        }
    }
}
