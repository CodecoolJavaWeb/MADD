package com.codecool.queststore.DAO;

import com.codecool.queststore.ConnectionProvider;
import com.codecool.queststore.controller.AuthenticationController;
import com.codecool.queststore.model.Admin;
import com.codecool.queststore.model.Mentor;
import com.codecool.queststore.model.Student;
import com.codecool.queststore.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Integer idUser;
    private AuthenticationController authController;
    private Connection connection;
    private User user;

    private static final String GET_USER_BY_ID = "SELECT * FROM app_user WHERE id_user = ?;";

    public UserDAO(AuthenticationController authenticationController) {
        authController = authenticationController;
        connection = ConnectionProvider.getConnection();
    }

    public User getUserById() {
        idUser = authController.getUserId();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return extractUserFromRow(resultSet);
            } else {
                return null;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User extractUserFromRow(ResultSet resultSet) throws SQLException{
        Integer userId = resultSet.getInt("id_user");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String phoneNumber = resultSet.getString("phone");
        String email = resultSet.getString("email");
        String role = resultSet.getString("role");

        switch (role) {
            case "admin":
                user = new Admin(userId, firstName, lastName, phoneNumber, email, role);
                break;
            case "mentor":
                user = new Mentor(userId, firstName, lastName, phoneNumber, email, role);
                break;
            case "student":
                user = new Student(userId, firstName, lastName, phoneNumber, email, role);
                break;
        }

        return user;
    }

}
