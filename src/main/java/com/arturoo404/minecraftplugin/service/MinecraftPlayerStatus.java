package com.arturoo404.minecraftplugin.service;

import com.arturoo404.minecraftplugin.service.database.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MinecraftPlayerStatus {
    private final DataBase dataBase = new DataBase();

    //TODO Update player name in database after change his name on mojang page
    public void playerOnline(boolean online, String player, String uuid) throws SQLException {
        if (playerInDb(uuid)){
            PreparedStatement statement = dataBase.getConnection()
                    .prepareStatement("UPDATE minecraft_player SET player_online = ? WHERE uuid = ?");
            statement.setBoolean(1, online);
            statement.setString(2, uuid);
            statement.executeUpdate();

            statement.close();
        }else {
            PreparedStatement statement = dataBase.getConnection()
                    .prepareStatement("INSERT INTO minecraft_player(player_name,uuid, player_online) VALUES (?, ?, ?)");
            statement.setString(1, player);
            statement.setString(2, uuid);
            statement.setBoolean(3, online);
            statement.executeUpdate();

            statement.close();
        }
    }

    private boolean playerInDb(String uuid) throws SQLException {
        PreparedStatement statement = dataBase.getConnection().prepareStatement("SELECT * FROM minecraft_player WHERE uuid = ?");
        statement.setString(1, uuid);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            String uuidFromDb = resultSet.getString("uuid");
            if (uuidFromDb.equals(uuid)){
                statement.close();
                return true;
            }
        }
        statement.close();

        return false;
    }
}
