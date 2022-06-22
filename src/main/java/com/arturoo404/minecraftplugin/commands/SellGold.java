package com.arturoo404.minecraftplugin.commands;

import com.arturoo404.minecraftplugin.service.database.DataBase;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SellGold implements CommandExecutor {

    private DataBase dataBase = new DataBase();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§6Coin")){
                try {
                    sellItem(1L, p.getUniqueId().toString(), p.getInventory().getItemInMainHand().getAmount());
                    p.sendMessage(ChatColor.DARK_AQUA + "You received: " + ChatColor.GOLD + p.getInventory().getItemInMainHand().getAmount());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                p.getInventory().getItemInMainHand().setAmount(0);
                return false;
            }
            if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§eHeavy Ingot")){
                try {
                    sellItem(10L, p.getUniqueId().toString(), p.getInventory().getItemInMainHand().getAmount());
                    p.sendMessage(ChatColor.DARK_AQUA + "You received: " + ChatColor.GOLD + p.getInventory().getItemInMainHand().getAmount() * 10);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                p.getInventory().getItemInMainHand().setAmount(0);
                return false;
            }
        }
        return false;
    }

    private void sellItem(Long value, String uuid, int itemNumber) throws SQLException {
        long money = currentMoney(uuid) + value * itemNumber;

        PreparedStatement statement = dataBase.getConnection()
                .prepareStatement("UPDATE minecraft_player SET money = ? WHERE uuid = ?");
        statement.setLong(1, money);
        statement.setString(2, uuid);
        statement.executeUpdate();

        statement.close();
    }

    private Long currentMoney(String uuid) throws SQLException {
        PreparedStatement statement = dataBase.getConnection()
                .prepareStatement("SELECT money FROM minecraft_player WHERE uuid = ?");
        statement.setString(1, uuid);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            Long money = resultSet.getLong("money");
            statement.close();
            return money;
        }
        statement.close();
        return null;
    }
}
