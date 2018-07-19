package com.codecool.queststore.DAO;

import com.codecool.queststore.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;

public class MentorDAO {

    private Connection connection;

    public MentorDAO() throws SQLException {
        connection = new ConnectionProvider().getConnection();
    }
}
