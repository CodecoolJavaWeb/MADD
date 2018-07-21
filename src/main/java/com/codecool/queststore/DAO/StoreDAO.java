package com.codecool.queststore.DAO;

import com.codecool.queststore.ConnectionProvider;
import com.codecool.queststore.model.Artifact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO {

    private Connection connection = new ConnectionProvider().getConnection();
    private List<Artifact> studentArtifactList;
    private Artifact artifact;

    public StoreDAO() {
        this.studentArtifactList = new ArrayList<>();
        addArtifactToList();
    }

    private static final String GET_ARTIFATS = "SELECT * FROM studentArtifact;";
    private static final String GET_QUANTITY = "SELECT quantity FROM student_Artifact WHERE id_student = ? AND id_artifact = ?;";
    private static final String INSERT_VALUES_IF_NEW_ARTIFACT = "INSERT INTO student_artifact(id_student, id_artifact, quantity) VALUES (?, ?, ?);";

    public void addArtifactToList() {

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ARTIFATS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Artifact artifact = extractArtifactFromRow(resultSet);
                studentArtifactList.add(artifact);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private Artifact extractArtifactFromRow(ResultSet resultSet) throws SQLException{

        Integer id_artifact = resultSet.getInt("id_artifact");
        String artifact_name = resultSet.getString("artifact_name");
        Integer price = resultSet.getInt("price");
        String category = resultSet.getString("category");
        String description = resultSet.getString("description");

        artifact = new Artifact(id_artifact, artifact_name, price, category, description);
        return artifact;

    }
    public List<Artifact> getStudentArtifactList(){

        return this.studentArtifactList;
    }
    public void addArtifactToStudent(int studentID, int itemID){
        String UPDATE_VALUES_IF_ADD_ARTIFACT = "UPDATE student_artifact SET quantity = ? WHERE id_student = ? AND id_artifact = ?;";


        int quantity = checkQuantityOfItems(studentID, itemID);
        System.out.println("Quantity " + quantity);

        if(quantity >= 1){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VALUES_IF_ADD_ARTIFACT);
                preparedStatement.setInt(1, quantity);
                preparedStatement.setInt(2, studentID);
                preparedStatement.setInt(3, itemID);
                preparedStatement.executeUpdate();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VALUES_IF_NEW_ARTIFACT);
                preparedStatement.setInt(1, studentID);
                preparedStatement.setInt(2, itemID);
                preparedStatement.setInt(3, 1);
                preparedStatement.executeUpdate();

            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private int checkQuantityOfItems(int studentID, int itemID){

        Integer quantity = 0;

        try{

            PreparedStatement preparedStatement = connection.prepareStatement(GET_QUANTITY);
            preparedStatement.setInt(1, studentID);
            preparedStatement.setInt(2, itemID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                quantity = resultSet.getInt("quantity");
                System.out.println("duuupa " + quantity);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(quantity > 0){
            quantity++;
        }
        else{
            quantity = 0;
        }
        return quantity;

    }
}
