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
import java.util.List;

public class StudentController implements HttpHandler {
    private StudentDAO studentDAO;
    private AuthenticationController authenticationController;
    private StudentArtifactDAO studentArtifactDAO;
    private int userID;

    public StudentController(AuthenticationController authenticationController) throws SQLException {
        this.studentDAO = new StudentDAO();
        this.authenticationController = authenticationController;
        this.studentArtifactDAO = new StudentArtifactDAO(this);

    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        System.out.println("method " + method);
        String response = "";
        System.out.println("HERE CodeColer");
        this.userID= this.authenticationController.getUserId();


        if (method.equals("GET")) {

         //   int studentID = getStudentID(this.userID);

            List<Integer> listIDOfStudents = studentArtifactDAO.getArifcatQuantiyAndID(getStudentID(this.userID));
            studentArtifactDAO.getArtifactMetaData(listIDOfStudents);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/codecooler.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("studentMoney", studentDAO.getStudentMoney(getStudentID(this.userID)));
            model.with("userName",  studentDAO.getStudentName(this.userID));
            model.with("artifacts", studentArtifactDAO.getArtifactList());

            response = template.render(model);
            // model.with("quanity", studentArtifactDAO.getStudentArtifactList());
        }
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public int getUserID(){
        return this.userID;

    }
    public int getStudentID(int user){
        int studentID = this.studentDAO.getStudentIDToStudentController(user);
        return studentID;
    }
}


