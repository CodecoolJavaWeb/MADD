package com.codecool.queststore.controller;

import com.codecool.queststore.DAO.QuestDAO;
import com.codecool.queststore.DAO.StudentDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;

public class StudentQuestController implements HttpHandler {

    private AuthenticationController authenticationController;
    private StudentDAO studentDAO;
    private int userID;
    private int studentID;

    public StudentQuestController(AuthenticationController authenticationController) {
        this.authenticationController = authenticationController;
        this.studentDAO = new StudentDAO();
    }

    @Override

    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();
        System.out.println("method " + method);
        String response = "";
        System.out.println("HERE queststudent");

        this.userID = this.authenticationController.getUserId();
        this.studentID = this.studentDAO.getStudentIDToStudentController(userID);


        if (method.equals("GET")) {

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/quests.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("studentMoney", studentDAO.getStudentMoney(studentID));
            model.with("userName", studentDAO.getStudentName(userID));
            model.with("quests", new QuestDAO().getQuests());

            response = template.render(model);
        }
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }
}
