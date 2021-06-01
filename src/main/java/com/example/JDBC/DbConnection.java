package com.example.JDBC;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DbConnection {
    private Connection connection;

    public DbConnection() throws ClassNotFoundException, SQLException, IOException {
        Properties prop = new Properties();

        // only way to get this to work is using absolute path its not ideal
        InputStream input = new FileInputStream("PLACE PATH TO config.properties HERE");
        prop.load(input);


        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        if(this.connection != null){
            this.connection.close();
        }
    }
}
