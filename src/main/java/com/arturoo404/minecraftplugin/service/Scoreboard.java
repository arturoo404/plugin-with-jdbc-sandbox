package com.arturoo404.minecraftplugin.service;

import com.arturoo404.minecraftplugin.MinecraftPlugin;
import com.arturoo404.minecraftplugin.service.database.DataBase;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Scoreboard{

    private DataBase dataBase = new DataBase();
    public  void scoreBoard(Player player) throws SQLException {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Test", "new", player.getDisplayName());
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(scoreboard);
        Score score = objective
                .getScore(ChatColor.DARK_RED + "Your money: " + ChatColor.GOLD + getMoney(player.getUniqueId().toString()));
        score.setScore(0);
    }

    private int getMoney(String uuid) throws SQLException {
        PreparedStatement statement = dataBase.getConnection()
                .prepareStatement("SELECT money FROM minecraft_player WHERE uuid = ?");
        statement.setString(1, uuid);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            int id = Math.toIntExact(resultSet.getLong("money"));
            statement.close();
            return id;
        }
        statement.close();
        return 0;
    }

}
