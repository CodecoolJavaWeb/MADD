package com.codecool.queststore.DAO;

import com.codecool.queststore.ConnectionProvider;

import java.sql.Connection;

public class MentorDAO {

    private Connection connection;

    public MentorDAO()  {
        connection = new ConnectionProvider().getConnection();
    }
}
