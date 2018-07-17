package com.codecool.queststore.DAO;

import com.codecool.queststore.ConnectionProvider;
import com.codecool.queststore.controller.AuthenticationController;
import com.codecool.queststore.model.Authentication;
import com.codecool.queststore.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AuthenticationDAO {

    private Connection connection;

    private static final String GET_USER_BY_LOGIN =
            "SELECT id_user " +
                    "FROM authentication " +
                    "WHERE login = ? " +
                    "AND password = ?;";

    public AuthenticationDAO()  {
        connection = new ConnectionProvider().getConnection();
    }

    public Integer getUserIdByInputs(Map<String, String> formData) {
        String login = formData.get("login");
        String password = formData.get("password");
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                Integer userId = resultSet.getInt("id_user");
                return userId;
            } else {
                System.out.println("no user was found with given login pass");
                return null;
            }
        } catch(SQLException e) {
            System.out.println("db error");
            e.printStackTrace();
        }
        System.out.println("xfiles");
        return null;
    }
}
