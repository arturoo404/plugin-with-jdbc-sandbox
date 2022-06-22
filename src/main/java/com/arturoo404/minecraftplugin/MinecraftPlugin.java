package com.arturoo404.minecraftplugin;

import com.arturoo404.minecraftplugin.commands.ConnectWebAccount;
import com.arturoo404.minecraftplugin.commands.SellGold;
import com.arturoo404.minecraftplugin.listener.BlockEvent;
import com.arturoo404.minecraftplugin.listener.PlayerServerEvent;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftPlugin extends JavaPlugin implements Listener{

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BlockEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerServerEvent(this), this);
        getCommand("connect_web_account").setExecutor(new ConnectWebAccount());
        getCommand("sell_gold").setExecutor(new SellGold());
    }

}
