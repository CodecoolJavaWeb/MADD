package com.codecool.queststore.controller;

import com.codecool.queststore.ConnectionProvider;
import com.codecool.queststore.DAO.StudentArtifactDAO;
import com.codecool.queststore.DAO.StudentDAO;
import com.codecool.queststore.model.Artifact;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class StoreBuyOneController implements HttpHandler {

    private AuthenticationController authenticationController;
    private StudentDAO studentDAO;
    private Connection connection = new ConnectionProvider().getConnection();
    private StoreController storeController;
    private StudentArtifactDAO studentArtifactDAO;

    public StoreBuyOneController(AuthenticationController authenticationController, StoreController storeController){
        this.authenticationController = authenticationController;
        this.studentDAO = new StudentDAO();
        this.storeController = storeController;
        this.studentArtifactDAO = new StudentArtifactDAO();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String response = "";
        System.out.println("HERE storebyone");


        if (method.equals("GET")) {

            int userID = getUserID();
            int studentID = getStudentID(userID);
            int itemID = Integer.parseInt(storeController.getMap().get("BUY"));
            List<Artifact> artifactList = studentArtifactDAO.getArtifactByID(itemID);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/store-buy-one.twig");
            JtwigModel model = JtwigModel.newModel();


            model.with("artifacts", studentArtifactDAO.getArtifactByID(itemID));
            response = template.render(model);
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
