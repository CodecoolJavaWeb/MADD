package com.codecool.queststore.controller;

import com.codecool.queststore.DAO.MentorDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.sql.SQLException;

public class MentorController implements HttpHandler {

    private MentorDAO mentorDAO;
    private Map<String, String> formData;

    public MentorController() throws SQLException {
        mentorDAO = new MentorDAO();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String response = "";


        if (method.equals("POST") && parsePath(httpExchange)[2].equals("createstudent")) {
            //read inputs into formData map.
            Map<String, String> studentData = parseInputs(httpExchange);
            mentorDAO.addStudentToUsersTable(studentData);
            mentorDAO.getStudentUserId(studentData);
            mentorDAO.addStudentToStudentsTable(studentData);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/mentorstudents.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("studentList", mentorDAO.getStudents());
            response = template.render(model);
            httpRedirectTo("/mentors", httpExchange);
            httpExchange.sendResponseHeaders(200, response.length());
        }
        else if (method.equals("POST") && parsePath(httpExchange)[2].equals("editstudent")){

        }

        if (method.equals("GET") && parsePath(httpExchange).length <=2) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/mentorstudents.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("studentList", mentorDAO.getStudents());
            response = template.render(model);
            httpExchange.sendResponseHeaders(200, response.length());
        }
        else if (method.equals("GET") && parsePath(httpExchange)[2].equals("createstudent")) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/createstudent.twig");
            JtwigModel model = JtwigModel.newModel();
            response = template.render(model);
            httpExchange.sendResponseHeaders(200, response.length());
        }
        else if (method.equals("GET") && parsePath(httpExchange)[2].equals("editstudent")){
            JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/editstudent.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("student", mentorDAO.getStudentById(Integer.valueOf(parsePath(httpExchange)[3])));
            response = template.render(model);
            httpExchange.sendResponseHeaders(200, response.length());
        }
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    private Map<String, String> parseInputs (HttpExchange httpExchange) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();

        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "UTF8");
        BufferedReader br = new BufferedReader(isr);
        String inputs = null;
        try {
            inputs = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] pairs = inputs.split("&");
        for (String element : pairs) {
            String[] keyValue = element.split("=");
            String value = URLDecoder.decode(keyValue[1], "UTF8");
            map.put(keyValue[0], value);
        }
        return map;
    }

    private String[] parsePath(HttpExchange httpExchange) {
        String[] pathArray = httpExchange.getRequestURI().getPath().split("/");
        for (String element : pathArray) {
            System.out.println(element);
        }
        return pathArray;
    }

    private void httpRedirectTo(String dest, HttpExchange httpExchange) throws IOException {
        String hostPort = httpExchange.getRequestHeaders().get("host").get(0);
        System.out.println(hostPort);
        httpExchange.getResponseHeaders().set("Location", "http://" + hostPort + dest);
        httpExchange.sendResponseHeaders(301, -1);
    }
}
