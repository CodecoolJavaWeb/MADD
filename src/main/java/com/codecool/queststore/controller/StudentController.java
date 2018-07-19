package com.codecool.queststore.controller;

import com.codecool.queststore.DAO.StudentDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;

public class StudentController implements HttpHandler {
    private StudentDAO studentDAO;

    public StudentController() {
        this.studentDAO = new StudentDAO();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException{
        String method = httpExchange.getRequestMethod();
        String response = "";

        if(method.equals("GET")) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/codecooler.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("ArtifactList", studentDAO.getStudentArtifactList());
            model.with("money", studentDAO.getStudentCurrentMoney());
            model.with("totalmoney", studentDAO.getStudentTotalMoney());
            response = template.render(model);
        }


    }

    }
