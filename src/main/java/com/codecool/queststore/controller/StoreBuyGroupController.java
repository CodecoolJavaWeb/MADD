package com.codecool.queststore.controller;

import com.codecool.queststore.DAO.StudentDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;

public class StoreBuyGroupController implements HttpHandler {

    private AuthenticationController authenticationController;
    private StudentDAO studentDAO;

    public StoreBuyGroupController(AuthenticationController authenticationController) {
        this.authenticationController = authenticationController;
        this.studentDAO = new StudentDAO();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        System.out.println("method " + method);
        String response = "";
        System.out.println("HERE storegruop");


        if (method.equals("GET")) {

            int userID = getUserID();
            int studentID = getStudentID(userID);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/incubator.twig");
            JtwigModel model = JtwigModel.newModel();

            model.with("userName",  studentDAO.getStudentName(userID));
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
