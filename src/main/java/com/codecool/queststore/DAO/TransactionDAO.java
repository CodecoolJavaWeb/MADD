package com.codecool.queststore.DAO;

import com.codecool.queststore.ConnectionProvider;
import com.codecool.queststore.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    private Connection connection = new ConnectionProvider().getConnection();



    private static final String GET_ALL_STUDENT_TRANSACTION =
            "SELECT studentArtifact.artifact_name, studentArtifact.category, studentArtifact.description FROM student\n"+
            "    JOIN  student_2_buyers_group\n"+
            "        ON student.id_student = student_2_buyers_group.id_student\n"+
            "    JOIN group_transaction\n"+
            "        ON student_2_buyers_group.id_group = group_transaction.id_group\n"+
            "    JOIN studentArtifact\n"+
            "        ON group_transaction.id_artifact = studentArtifact.id_artifact\n"+
            "UNION \n"+
            "SELECT  studentArtifact.artifact_name, studentArtifact.category, studentArtifact.description FROM student\n"+
            "    JOIN student_transaction\n"+
            "        ON student.id_student = student_transaction.id_student\n"+
            "    JOIN studentArtifact\n"+
            "        ON student_transaction.id_artifact = studentArtifact.id_artifact\n"+
            "WHERE student.id_student = ?;";

    public List<Transaction> getAllStudentTransaction(int studentID){

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.clear();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_STUDENT_TRANSACTION);
            preparedStatement.setInt(1, studentID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String artifact_name = resultSet.getString("artifact_name");
                String category = resultSet.getString("category");
                String description = resultSet.getString("description");
                Transaction transaction = new Transaction(artifact_name, category, description);
                transactionList.add(transaction);
            }
        }
        catch (SQLException ex){
            System.out.println(ex);
        }
        return transactionList;
    }
    public void addItemToHistoryTransactioN(int studentID, int itemID){

        String UPDATE_VALUES_IF_ADD_ARTIFACT = "INSERT INTO student_transaction (id_student, id_artifact) VALUES(?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VALUES_IF_ADD_ARTIFACT);
            preparedStatement.setInt(1, studentID);
            preparedStatement.setInt(2, itemID);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
