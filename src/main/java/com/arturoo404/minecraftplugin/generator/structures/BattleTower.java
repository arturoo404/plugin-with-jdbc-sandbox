package com.arturoo404.minecraftplugin.generator.structures;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BattleTower {

    private int locX;
    private int locY;
    private int locZ;

    public BattleTower(int locX, int locY, int locZ) {
        this.locX = locX;
        this.locY = locY;
        this.locZ = locZ;
    }

    public void generateBattleTower(){
        generateWalls();
    }


    private void generateWalls(){
        for (int i = this.locY; i < this.locY + 30; i++){
            Location loc = new Location(Bukkit.getWorld("world"), this.locX, i, this.locZ);
            generateXWalls(loc);
            generateXWallOps(loc);
        }
        for (int i = this.locY; i < this.locY + 30; i++){
            Location loc = new Location(Bukkit.getWorld("world"), this.locX, i, this.locZ);
            generateYWalls(loc);
            generateYWallsOps(loc);
        }
    }

    private void generateXWalls(Location location){
        for (int i = 4; i < 12; i++) {
            location.setX(locX + i);
            location.getBlock().setType(Material.STONE_BRICKS);
        }
    }
    private void generateXWallOps(Location location){
        location.setZ(locZ + 15);
        for (int i = 4; i < 12; i++) {
            location.setX(locX + i);
            location.getBlock().setType(Material.STONE_BRICKS);
        }
    }

    private void generateYWalls(Location location){
        for (int i = 4; i < 12; i++) {
            location.setZ(locZ + i);
            location.getBlock().setType(Material.STONE_BRICKS);
        }
    }

    private void generateYWallsOps(Location location){
        location.setX(locX + 15);
        for (int i = 4; i < 12; i++) {
            location.setZ(locZ + i);
            location.getBlock().setType(Material.STONE_BRICKS);
        }
    }

}
