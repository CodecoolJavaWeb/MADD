package com.codecool.queststore.DAO;

import com.codecool.queststore.ConnectionProvider;
import com.codecool.queststore.controller.AuthenticationController;
import com.codecool.queststore.model.Artifact;
import com.codecool.queststore.model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private Integer userId;
    private Integer studentId;
    private AuthenticationController authController;
    private Connection connection = new ConnectionProvider().getConnection();


    private static final String GET_STUDENT_ID =
            "SELECT id_student FROM student WHERE id_user = ?;";

    private static final String GET_STUDENT_CURRENTMONEY =
            "SELECT current_money FROM student WHERE id_user = ?";

    private static final String GET_STUDENT_TOTALMONEY =
            "SELECT total_money FROM student WHERE id_user = ?";

    private static final String GET_STUDENT_ARTIFACT =
            "SELECT artifact.artifact_name FROM student\n" +
                    "JOIN  student_2_buyers_group\n" +
                    "ON student.id_student = student_2_buyers_group.id_student\n" +
                    "JOIN group_transaction\n" +
                    "ON student_2_buyers_group.id_group = group_transaction.id_group\n" +
                    "JOIN artifact\n" +
                    "ON group_transaction.id_artifact = artifact.id_artifact \n" +
                    "WHERE student.id_student = ?\n" +
                    "UNION\n" +
                    "SELECT  artifact.artifact_name, artifact.description, artifact.category FROM student \n" +
                    "JOIN student_artifact \n" +
                    "ON student.id_student = student_artifact.id_student \n" +
                    "JOIN artifact \n" +
                    "ON student_artifact.id_artifact = artifact.id_artifact \n" +
                    "WHERE student.id_student = ?";

    public Integer getStudentId() throws Exception {
        userId = new AuthenticationController().getUserId();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                Integer studentId = resultSet.getInt("id_student");
//                connection.close();
//                preparedStatement.close();
                return studentId;
            } else {
                return null;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getStudentCurrentMoney() throws SQLException{
        userId = new AuthenticationController().getUserId();

        PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_CURRENTMONEY);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            //Integer studentCurrentMoney = resultSet.getInt("current_money");
            //return studentCurrentMoney
            return resultSet.getInt("current_money");
        } else {
            return null;
        }

    }

    public Integer getStudentTotalMoney() throws SQLException {
        userId = new AuthenticationController().getUserId();

        PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_TOTALMONEY);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            //Integer studentTotalMoney = resultSet.getInt("total_money");
            //return studentTotalMoney
            return resultSet.getInt("total_money");
        } else {
            return null;
        }

    }

    public List<Artifact> getStudentArtifactList() throws Exception {
        studentId = getStudentId();
        List<Artifact> studentArtifactList = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_ARTIFACT);
        preparedStatement.setInt(1, studentId);
        preparedStatement.setInt(2, studentId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String artifact_name = resultSet.getString("artifact_name");
            String description = resultSet.getString("description");
            String category = resultSet.getString("category");

            Artifact nextArtifact = new Artifact(artifact_name, category, description );
            studentArtifactList.add(nextArtifact);
        }
        preparedStatement.close();
        return studentArtifactList;
    }

}
