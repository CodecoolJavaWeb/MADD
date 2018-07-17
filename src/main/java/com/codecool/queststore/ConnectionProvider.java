package com.codecool.queststore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionProvider {
//    private static final String DB_PROPERTIES_FILE;
//    private static final Properties DB_CONNECTION_PROPERTIES;
//
//    private static String DB_URL;
//    private static String DB_USERNAME;
//    private static String DB_PASSWORD;

    private Connection connection;
    private final String url = "jdbc:postgresql://localhost:5432/queststore";
    private final String user = "postgres";
    private final String password = "dima1234";

    public ConnectionProvider() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Failed to create JDBC db connection " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
//    static {
//        DB_PROPERTIES_FILE = "database.properties";
//        DB_CONNECTION_PROPERTIES = new Properties();
//
//        try {
//            Path propertiesFilePath = Paths.get("src/main/resources", DB_PROPERTIES_FILE);
//            DB_CONNECTION_PROPERTIES.load(Files.newInputStream(propertiesFilePath));
//
//            DB_URL = DB_CONNECTION_PROPERTIES.getProperty("javabase.jdbc.url");
//            DB_USERNAME = DB_CONNECTION_PROPERTIES.getProperty("javabase.jdbc.username");
//            DB_PASSWORD = DB_CONNECTION_PROPERTIES.getProperty("javabase.jdbc.password");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static Connection getConnection() {
//        if (connection == null) {
//            try {
//                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//            } catch (SQLException e) {
//                System.out.println("no cdb onnection");
//            }
//        }
//        return connection;
//    }
}