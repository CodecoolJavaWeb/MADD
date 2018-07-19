package com.codecool.queststore.controller;

import com.codecool.queststore.DAO.MentorDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.sql.SQLException;

public class MentorController implements HttpHandler {

    private MentorDAO mentorDAO;

    public MentorController() throws SQLException {
        mentorDAO = new MentorDAO();
    }

    @Override
    public void handle(HttpExchange httpExchange){

        String method = httpExchange.getRequestMethod();
        String response = "";


    }
}
