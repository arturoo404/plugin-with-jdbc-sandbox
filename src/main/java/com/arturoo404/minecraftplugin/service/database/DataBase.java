package com.arturoo404.minecraftplugin.service.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

    private  Connection connection;

    public Connection getConnection() throws SQLException {
        if(connection != null){
            return connection;
        }

        String url = "jdbc:mysql://localhost:3306/minecraft";
        String user = "libraryUser";
        String password = "libraryUser";

        Connection connection = DriverManager.getConnection(url, user, password);

        this.connection = connection;

        return connection;
    }

}
