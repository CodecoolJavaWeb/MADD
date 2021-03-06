package com.codecool.queststore.DAO;

import com.codecool.queststore.ConnectionProvider;
import com.codecool.queststore.controller.StudentController;
import com.codecool.queststore.model.Artifact;
import com.codecool.queststore.model.StudentArtifact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentArtifactDAO {
    private List<StudentArtifact> studentArtifactList;
    private StudentController studentController;
    private Connection connection;
    private StudentArtifact studentArtifact;
    private Artifact artifact;
    private List<Artifact> artifactList;

    private static final String GET_ALL_ARTIFACTS = "SELECT * FROM student_artifact;";
    private static final String GET_ARTIFACTS_BY_ID_STUDENT = "SELECT id_artifact, quantity FROM student_artifact WHERE id_student = ?;";
    private static final String GET_ARTIFACTS_BY_ID_ARTIFACT = "SELECT * FROM studentArtifact WHERE id_artifact = ?;";


    public StudentArtifactDAO(StudentController studentController) throws SQLException {
        this.studentController = studentController;
        this.studentArtifactList = new ArrayList<>();
        this.artifactList = new ArrayList<>();
        connection = ConnectionProvider.getConnection();
        addArtifactToList();
    }
    public List<StudentArtifact> getStudentArtifactList(){

        return this.studentArtifactList;
    }

    public void addArtifactToList() {

        try{

            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ARTIFACTS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {

                StudentArtifact studentArtifact = extractArtifactFromRow(resultSet);
                studentArtifactList.add(studentArtifact);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

    private StudentArtifact extractArtifactFromRow(ResultSet resultSet) throws SQLException{

        Integer id_student = resultSet.getInt("id_student");
        Integer id_artifact = resultSet.getInt("id_artifact");
        Integer quantity = resultSet.getInt("quantity");
        studentArtifact = new StudentArtifact(id_student, id_artifact, quantity);

        return studentArtifact;
    }

    public List<Integer> getArifcatQuantiyAndID(int studentID) throws SQLException {

        int artifactID = 0;
        int quanity = 1;
        Integer[] artifact = new Integer[2];
        List<Integer> listIDOfStudent = new ArrayList<>();


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ARTIFACTS_BY_ID_STUDENT);
            preparedStatement.setInt(1, studentID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                listIDOfStudent.add(resultSet.getInt("id_artifact"));
              //  artifact[quanity] = resultSet.getInt("quantity");
                System.out.println("dupa " + studentID);
                System.out.println("artifacttt id"  + resultSet.getInt("id_artifact"));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return listIDOfStudent;
    }

    public void getArtifactMetaData(List<Integer> listIDOfStudents){

        for(int id : listIDOfStudents){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ARTIFACTS_BY_ID_ARTIFACT);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {
                    int idArtifact = resultSet.getInt("id_artifact");
                    String artifact_name = resultSet.getString("artifact_name");
                    int price = resultSet.getInt("price");
                    String category = resultSet.getString("category");
                    String description = resultSet.getString("description");
                    artifact = new Artifact(idArtifact, artifact_name, price, category, description);
                    this.artifactList.add(artifact);
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public List<Artifact> getArtifactList(){
        return this.artifactList;
    }
}

