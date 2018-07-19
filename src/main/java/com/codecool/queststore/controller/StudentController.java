package com.codecool.queststore.controller;

import com.codecool.queststore.DAO.StudentArtifactDAO;
import com.codecool.queststore.DAO.StudentDAO;
import com.codecool.queststore.model.Artifact;
import com.codecool.queststore.model.StudentArtifact;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class StudentController implements HttpHandler {
    private StudentDAO studentDAO;
    private AuthenticationController authenticationController;
    private StudentArtifactDAO studentArtifactDAO;

    public StudentController(AuthenticationController authenticationController) throws SQLException {
        this.studentDAO = new StudentDAO();
        this.authenticationController = authenticationController;
        this.studentArtifactDAO = new StudentArtifactDAO(this);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        int id = 0;
        int quanity = 1;
        String method = httpExchange.getRequestMethod();
        System.out.println("method " + method);
        String response = "";
        System.out.println("HERE CodeColer");


        if (method.equals("GET")) {
            int artifactID = 0;

            try {
                int userID = getUserID();
                int studentID = getStudentID(userID);


                studentArtifactDAO.getStudentArtifactList();

                Integer[] artifact = studentArtifactDAO.getArifcatQuantiyAndID(studentID);
                studentArtifactDAO.getArtifactMetaData(artifact[artifactID]);

                JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/codecooler.twig");
                JtwigModel model = JtwigModel.newModel();


                model.with("artifacts", studentArtifactDAO.getArtifactList());
               // model.with("quanity", studentArtifactDAO.getStudentArtifactList());

                response = template.render(model);


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private int getUserID(){
        int userID = this.authenticationController.getUserId();
        return userID;

    }
    public int getStudentID(int user){
        int studentID = this.studentDAO.getStudentIDToStudentController(user);
        return studentID;
    }
}


