package com.arturoo404.minecraftplugin;

import com.arturoo404.minecraftplugin.commands.ConnectWebAccount;
import com.arturoo404.minecraftplugin.commands.SellGold;
import com.arturoo404.minecraftplugin.listener.BlockEvent;
import com.arturoo404.minecraftplugin.listener.PlayerServerEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

public final class MinecraftPlugin extends JavaPlugin implements Listener{

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BlockEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerServerEvent(), this);
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("connect_web_account").setExecutor(new ConnectWebAccount());
        getCommand("sell_gold").setExecutor(new SellGold());
    }



    @EventHandler
    public void create(PlayerJoinEvent event){
        scoreBoard(event.getPlayer());
    }

    public void scoreBoard(Player player){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Test", "new", player.getDisplayName());
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = objective.getScore(ChatColor.LIGHT_PURPLE + "+++++++++++");
        score.setScore(3);
        player.setScoreboard(scoreboard);
    }

}
