package com.codecool.queststore.DAO;

import com.codecool.queststore.ConnectionProvider;
import com.codecool.queststore.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MentorDAO {

    private Integer userId;
    private Integer classId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String role;
    private Integer currentMoney;
    private Integer totalMoney;
    private String studentClassName;

    //private Connection connection = null;
    private static final String GET_STUDENTS =
            "SELECT * FROM app_user " +
                    "JOIN student ON (app_user.id_user = student.id_user) " +
                    "JOIN cool_class ON (student.id_class = cool_class.id_class);";

    private Connection connect() {
        return new ConnectionProvider().getConnection();
    }

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        try (Connection connection = this.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENTS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                userId = resultSet.getInt("id_user");
                firstName = resultSet.getString("first_name");
                lastName = resultSet.getString("last_name");
                phone = resultSet.getString("phone");
                email = resultSet.getString("email");
                role = resultSet.getString("role");
                currentMoney = resultSet.getInt("current_money");
                totalMoney = resultSet.getInt("total_money");
                studentClassName = resultSet.getString("class_name");

                Student student = new Student(userId, firstName, lastName, phone, email, role, currentMoney, totalMoney, studentClassName);
                students.add(student);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    //First stage of adding student (add data to app_user table)
    public void addStudentToUsersTable(Map<String, String> studentData) {
        firstName = studentData.get("name");
        lastName = studentData.get("surname");
        email = studentData.get("email");
        phone = studentData.get("phone");
        role = "student";

        String sql1 = "INSERT INTO app_user (first_name,last_name, phone, email, role) VALUES(?,?,?,?,?)";
        try (Connection connection = this.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, role);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Second stage of adding student (get id_user of the student from app_data table)
    public Integer getStudentUserId(Map<String, String> studentData) {
        email = studentData.get("email");

        String sql = "SELECT id_student FROM app_user WHERE email = ?";
        try (Connection connection = this.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userId = resultSet.getInt("id_user");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }

    //Third stage of adding student (get id_class of the class name to which the student was added from cool_class table)
    public Integer getStudentClassId(Map<String, String> studentData) {
        studentClassName = studentData.get("class");

        String sql = "SELECT id_class FROM cool_class WHERE class_name = ?";
        try (Connection connection = this.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, studentClassName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                classId = resultSet.getInt("id_class");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classId;
    }

    //Fourth stage of adding student (add data to students table)
    public void addStudentToStudentsTable(Map<String, String> studentData) {
        userId = getStudentUserId(studentData);
        classId = getStudentClassId(studentData);
        currentMoney = 0;
        totalMoney = Integer.parseInt(studentData.get("level"));

        String sql = "INSERT INTO student (id_user, id_class, current_money, total_money) VALUES(?,?,?,?)";
        try (Connection connection = this.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, classId);
            preparedStatement.setInt(3, currentMoney);
            preparedStatement.setInt(4, totalMoney);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
