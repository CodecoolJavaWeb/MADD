package com.codecool.queststore.controller;

import com.codecool.queststore.DAO.StudentDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class StudentController implements HttpHandler {
    private StudentDAO studentDAO;

    public StudentController() {
        this.studentDAO = new StudentDAO();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();
        String response = "";


    }

    }
