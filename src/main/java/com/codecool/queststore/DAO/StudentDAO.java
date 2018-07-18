package com.codecool.queststore.DAO;

import com.codecool.queststore.controller.AuthenticationController;
import com.codecool.queststore.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    private Integer userId;
    private AuthenticationController authController;
    private Connection connection;

    private static final String GET_STUDENT_ID = "SELECT id_student FROM student WHERE id_user = ?;";

    public Integer getStudentId() throws Exception {
        userId = new AuthenticationController().getUserId();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                Integer studentId = resultSet.getInt("id_student");
                connection.close();
                preparedStatement.close();
                return studentId;
            } else {
                return null;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
