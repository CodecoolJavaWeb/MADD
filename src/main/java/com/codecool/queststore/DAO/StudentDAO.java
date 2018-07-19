package com.codecool.queststore.DAO;

import com.codecool.queststore.ConnectionProvider;
import com.codecool.queststore.controller.AuthenticationController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentDAO {

    private Integer userId;
    private AuthenticationController authController;
    private Connection connection = new ConnectionProvider().getConnection();


    private static final String GET_STUDENT_ID =
            "SELECT id_student FROM student WHERE id_user = ?;";
    private static final String GET_ALL_STUDENTS =
            "SELECT * FROM student;";
    private static final String GET_STUDENT_NAME =
            "SELECT first_name FROM app_user WHERE id_user = ?;";
    private static final String GET_STUDENT_MONEY = "SELECT current_money FROM student WHERE id_student = ?;";

    public Integer getStudentId() {
        userId = new AuthenticationController().getUserId();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                Integer studentId = resultSet.getInt("id_student");
                return studentId;
            } else {
                return null;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Integer getStudentIDToStudentController(int userId) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Integer studentId = resultSet.getInt("id_student");
                return studentId;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getStudentName(int userId){

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_NAME);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String studentId = resultSet.getString("first_name");
                return studentId;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Integer getStudentMoney(int studentID){

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_MONEY);
            preparedStatement.setInt(1, studentID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int studentMoney = resultSet.getInt("current_money");
                return studentMoney;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
