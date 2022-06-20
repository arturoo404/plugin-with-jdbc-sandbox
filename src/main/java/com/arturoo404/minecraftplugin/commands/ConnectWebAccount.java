package com.arturoo404.minecraftplugin.commands;

import com.arturoo404.minecraftplugin.service.database.DataBase;
import com.arturoo404.minecraftplugin.exception.EmailException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectWebAccount implements CommandExecutor {
    private DataBase dataBase = new DataBase();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;

            if (args.length == 0){
                p.sendMessage("You need to define your email! " + "Command: " + "/connect_web_account <your email>");
            }else {
                String email = args[0];
                String uuid = p.getUniqueId().toString();
                try {
                    connectAccount(email, uuid);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (EmailException e) {
                    p.sendMessage(e.getMessage());
                }
            }
        }
        return false;
    }

    private void connectAccount(String email, String uuid) throws SQLException, EmailException {
        boolean webConnect = webAccountConnectToMc(email);
        boolean emailDb = emailInDb(email);

        if (webConnect){
            throw new EmailException("This account have already connected.");
        }
        if (!emailDb){
            throw new EmailException("We don't found your email in database.");
        }
        connectAccountSQL(email, uuid);
    }

    private void connectAccountSQL(String email, String uuid) throws SQLException, EmailException {
        Long id = playerID(uuid);
        if (id == null){
            throw new EmailException("Error");
        }

        PreparedStatement statement = dataBase.getConnection()
                .prepareStatement("UPDATE user SET minecraft_player = ? WHERE email = ?");
        statement.setLong(1, id);
        statement.setString(2, email);
        statement.executeUpdate();

        statement.close();

    }

    private Long playerID(String uuid) throws SQLException {
        PreparedStatement statement = dataBase.getConnection()
                .prepareStatement("SELECT * FROM minecraft_player WHERE uuid = ?");
        statement.setString(1, uuid);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            Long id = resultSet.getLong("id");
            statement.close();
            return id;
        }
        statement.close();
        return null;
    }

    private boolean webAccountConnectToMc(String email) throws SQLException {
            PreparedStatement statement = dataBase.getConnection()
                    .prepareStatement("SELECT minecraft_player FROM user WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            String minecraftPlayer = resultSet.getString("minecraft_player");
            statement.close();
            return minecraftPlayer != null;
        }
        statement.close();
        return true;
    }

    private boolean emailInDb(String email) throws SQLException {
        PreparedStatement statement = dataBase.getConnection()
                .prepareStatement("SELECT email FROM user WHERE email = ?");
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            String emailDb = resultSet.getString("email");
            statement.close();
            return emailDb.equals(email);
        }
        statement.close();
        return false;
    }
}
