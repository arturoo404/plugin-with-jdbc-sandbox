package com.arturoo404.minecraftplugin.commands;

import com.arturoo404.minecraftplugin.items.Wepons;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemGetCommends implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p){
            p.getInventory().setItemInMainHand(new Wepons().magicStick());
        }
        return false;
    }
}
