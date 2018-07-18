package com.codecool.queststore.controller;

import com.codecool.queststore.DAO.AuthenticationDAO;
import com.codecool.queststore.DAO.MentorDAO;
import com.codecool.queststore.DAO.UserDAO;
import com.codecool.queststore.model.Mentor;
import com.codecool.queststore.model.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URI;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class AdminController implements HttpHandler {

    public MentorDAO mentorDAO = new MentorDAO();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println(parsePath(httpExchange).length);
        String method = httpExchange.getRequestMethod();
        String response = "";


        if (method.equals("POST")) {
            System.out.println("post");
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "UTF8");
            BufferedReader br = new BufferedReader(isr);
            String inputs = br.readLine();

        }

        if (method.equals("GET") && parsePath(httpExchange).length <=3) {

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/admin/mentor.twig");
            System.out.println("tu");
            System.out.println(mentorDAO.getMentorsList());
//            JtwigModel model = JtwigModel.newModel().with("var", "World");
            JtwigModel model = JtwigModel.newModel().with("dupa", "HUJJJ");
            model.with("mentors", mentorDAO.getMentorsList());
            response = template.render(model);
            httpExchange.sendResponseHeaders(200, response.length());


        }
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private String[] parsePath(HttpExchange httpExchange) {
        String[] pathArray = httpExchange.getRequestURI().getPath().split("/");
        return pathArray;
    }
}