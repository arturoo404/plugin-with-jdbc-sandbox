package com.arturoo404.minecraftplugin.generator.structures;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;

import java.util.Objects;
import java.util.Random;

public class StarsEndPillars {

    private final int posX;

    private final int posZ;

    private final int posY = 60;

    public StarsEndPillars(int posX, int posZ) {
        this.posX = posX;
        this.posZ = posZ;
    }

    public void generatePillars(){
        generatePosOfPillars(structuresNumber());

    }


    private void generatePosOfPillars(int structuresNumber){
        if (structuresNumber != 0){
            for (int i = 0; i < structuresNumber; i++){
                int pillarsPosX = this.posX + randomPos();
                int pillarsPosZ = this.posZ + randomPos();
                Location loc = new Location(Bukkit.getWorld("world"), pillarsPosX, positionY(pillarsPosX, pillarsPosZ), pillarsPosZ);
                pillars(loc);
            }
        }
    }

    private void pillars(Location location){
        int sizeOfPillars = structuresNumber();
        int yLoc = location.getBlockY();
        if (yLoc != 0){
            switch (sizeOfPillars){
                case 0 -> {

                }

                case 1->{
                    location.setY(yLoc);
                    Objects.requireNonNull(location.getWorld()).generateTree(location, TreeType.CRIMSON_FUNGUS);
                }

                case 2 ->{

                }

                case 3 ->{

                }

                case 4 ->{

                }
            }
        }
    }

    private int height(int size){
        int s = 3 * size;

        return new Random()
                .ints(5, 15)
                .findFirst()
                .getAsInt();
    }
    private int positionY(int x, int z){
        Location loc = new Location(Bukkit.getWorld("world"), x, this.posY, z);
        for (int y = this.posY; y < this.posY + 50; y++){
            loc.setY(y);
            if (loc.getBlock().getType().equals(Material.AIR)){
                return y - 1;
            }
            if (loc.getBlock().getType().equals(Material.WATER)){
                return 0;
            }
        }
        return 70;
    }

    private int randomPos(){
        return new Random()
                .ints(0, 16)
                .findFirst()
                .getAsInt();
    }
    private int structuresNumber(){
        return new Random()
                .ints(0, 5)
                .findFirst()
                .getAsInt();
    }
}
