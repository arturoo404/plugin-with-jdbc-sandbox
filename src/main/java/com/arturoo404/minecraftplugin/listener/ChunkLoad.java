package com.arturoo404.minecraftplugin.listener;

import com.arturoo404.minecraftplugin.MinecraftPlugin;
import com.arturoo404.minecraftplugin.generator.structures.StarsEndPillars;
import com.arturoo404.minecraftplugin.generator.world.StarsEnd;
import org.bukkit.Chunk;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ChunkLoad implements Listener {


    private final MinecraftPlugin plugin;

    public ChunkLoad(MinecraftPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void chunkLoad(ChunkLoadEvent event){
        if (event.isNewChunk()){
            //&& event.getChunk().getChunkSnapshot().getBiome(8, 62, 8).equals(Biome.PLAINS)
//            int random = new Random()
//                    .ints(0, 30)
//                    .findFirst()
//                    .getAsInt();
//            if (random == 5){
//                System.out.println("WORK");
//                Chunk chunk = event.getChunk();
//                Block block = chunk.getBlock(0, 80, 0);
//                BattleTower battleTower = new BattleTower(block.getX(), block.getY(), block.getZ());
//                battleTower.generateBattleTower();
//            }
            Chunk chunk = event.getChunk();
            Block block = chunk.getBlock(0, 60, 0);
            StarsEnd starsEnd = new StarsEnd(block.getX(), block.getY(), block.getZ(), plugin);
            starsEnd.generateStarsEnd();

            new BukkitRunnable(){
                @Override
                public void run() {
                    StarsEndPillars pillars = new StarsEndPillars(block.getX(), block.getZ());
                    if (block.getBiome().equals(Biome.PLAINS)){
                        pillars.generatePillars();
                    }
                    cancel();
                }

            }.runTaskTimer(this.plugin, 0, 1);
        }
    }
}
