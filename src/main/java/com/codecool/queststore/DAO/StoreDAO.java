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
}
