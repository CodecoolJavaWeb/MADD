package com.codecool.queststore.DAO;

import com.codecool.queststore.ConnectionProvider;
import com.codecool.queststore.controller.AuthenticationController;
import com.codecool.queststore.model.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class StudentDAO{

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
    private static final String GET_STUDENT_TOTAL_MONEY = "SELECT total_money FROM student WHERE id_student = ?;";
    private static final String UPDATE_MONEY = "UPDATE student SET current_money = ? WHERE id_student = ?;";

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
            }
            else {
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
    public void updateMoney(int money, int studentID){

        int studentMoney = getStudentMoney(studentID);
        int moneyAfterBuy = (studentMoney - money);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MONEY);
            preparedStatement.setInt(1, moneyAfterBuy);
            preparedStatement.setInt(2, studentID);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Integer getStudentTotalMoney(int studentID){

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_TOTAL_MONEY);
            preparedStatement.setInt(1, studentID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int totalMoney = resultSet.getInt("total_money");
                return totalMoney;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getStudentLevel(int studentID){

        int totalMoney = getStudentTotalMoney(studentID);


        List<Level> levels = sortLevelsList();

        String level = Integer.toString(0);

        for(int i = 0; i < levels.size() - 1; i++){
            if (levels.get(i).getExperience() <= totalMoney && levels.get(i + 1).getExperience() > totalMoney){
                level = levels.get(i).getName();
            }
        }
        return level;
    }

    private List<Level> sortLevelsList(){

        List<Level> levels = new LevelDAO().getLevelsList();

        Collections.sort(levels, new Comparator<Level>() {
            @Override
            public int compare(Level expierence1, Level expierence2) {
                return expierence1.getExperience().compareTo(expierence2.getExperience());
            }
        });
        return levels;
    }
}
