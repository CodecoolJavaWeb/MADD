package com.codecool.queststore;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionProvider {
    private Connection connection;


    public ConnectionProvider(String name, String owner, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + name, owner, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }


    public Connection getConnection() {
        return connection;
    }
}