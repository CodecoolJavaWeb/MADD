package com.codecool.queststore.controller;

import com.codecool.queststore.DAO.LevelDAO;
import com.codecool.queststore.DAO.MentorDAO;
import com.codecool.queststore.model.Mentor;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import java.io.*;
import java.net.URLDecoder;


public class AdminController implements HttpHandler {
    private MentorDAO mentorDAO = new MentorDAO();
    private LevelDAO levelDAO = new LevelDAO();


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();
        String response = "";

        if (method.equals("POST") && parsePath(httpExchange).length >= 4) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "UTF8");
            BufferedReader br = new BufferedReader(isr);
            String inputs = br.readLine();

            if(parsePath(httpExchange)[2].equals("mentors") && parsePath(httpExchange)[3].equals("edit")) {
                Integer userId = Integer.valueOf(parsePath(httpExchange)[4]);
                Mentor mentor = mentorDAO.getMentorByUserId(userId);
                mentorDAO.updateMentorByUserId(mentorDAO.getMentorByUserId(userId), parseInputs(inputs,5));
                JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/admin/mentor.twig");
                JtwigModel model = JtwigModel.newModel();
                response = template.render(model);
                httpRedirectTo("/admin/mentors", httpExchange);
            }

            else if(parsePath(httpExchange)[2].equals("mentors") && parsePath(httpExchange)[3].equals("add")) {
                String[] parsedInputs = parseInputs(inputs,5);
                Mentor newMentor = mentorDAO.addUserToDB(parsedInputs[0], parsedInputs[1], parsedInputs[2], parsedInputs[3], "mentor");
                mentorDAO.addMentorToDB(newMentor);
                JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/admin/mentor.twig");
                JtwigModel model = JtwigModel.newModel();
                response = template.render(model);
                httpRedirectTo("/admin/mentors", httpExchange);
            }

            else if(parsePath(httpExchange)[2].equals("levels") && parsePath(httpExchange)[3].equals("add")) {
                String[] parsedInputs = parseInputs(inputs,2);
                Integer experience = Integer.valueOf(parsedInputs[1]);
                levelDAO.addLevelToDB(parsedInputs[0], experience);
                JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/admin/mentor.twig");
                JtwigModel model = JtwigModel.newModel();
                response = template.render(model);
                httpRedirectTo("/admin/levels", httpExchange);
            }
        }

        //localhost:8000/admin/mentors
        if (method.equals("GET") && parsePath(httpExchange).length == 3) {

            if(parsePath(httpExchange)[2].equals("mentors")) {
                JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/admin/mentor.twig");
                JtwigModel model = JtwigModel.newModel();
                model.with("mentors", mentorDAO.getMentorsList());
                response = template.render(model);
                httpExchange.sendResponseHeaders(200, response.length());
            }

            else if(parsePath(httpExchange)[2].equals("levels")) {
                JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/admin/level.twig");
                JtwigModel model = JtwigModel.newModel();
                model.with("levels", levelDAO.getLevelsList());
                response = template.render(model);
                httpExchange.sendResponseHeaders(200, response.length());
            }
        }



        if (method.equals("GET") && parsePath(httpExchange).length > 3) {

            if(parsePath(httpExchange)[2].equals("mentors") && parsePath(httpExchange)[3].equals("edit")) {
                JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/admin/mentor-editing.twig");
                JtwigModel model = JtwigModel.newModel();
                Integer mentorId = Integer.valueOf(parsePath(httpExchange)[4]);
                model.with("mentor", mentorDAO.getMentorByUserId(mentorId));
                response = template.render(model);
                httpExchange.sendResponseHeaders(200, response.length());
            }

            else if (parsePath(httpExchange)[2].equals("mentors") && parsePath(httpExchange)[3].equals("add")) {
                JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/admin/mentor-adding.twig");
                JtwigModel model = JtwigModel.newModel();
                response = template.render(model);
                httpExchange.sendResponseHeaders(200, response.length());
            }

            else if (parsePath(httpExchange)[2].equals("levels") && parsePath(httpExchange)[3].equals("add")) {
                JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/admin/level-adding.twig");
                JtwigModel model = JtwigModel.newModel();
                response = template.render(model);
                httpExchange.sendResponseHeaders(200, response.length());
            }
        }

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }


    private String[] parseInputs(String inputs, int numberOfForms) throws UnsupportedEncodingException {
        String[] parsedFormResult = new String[numberOfForms];
        String [] eachForm = inputs.split("&");
        int counter = 0;

        for (String element : eachForm) {
            String [] keyValue = element.split("=");
            String value = URLDecoder.decode(keyValue[1], "UTF8");
            parsedFormResult[counter] = value.trim();
            counter++;
        }
        return parsedFormResult;
    }


    private String[] parsePath(HttpExchange httpExchange) {
        String[] pathArray = httpExchange.getRequestURI().getPath().split("/");
        return pathArray;
    }


    private void httpRedirectTo(String dest, HttpExchange httpExchange) throws IOException {
        String hostPort = httpExchange.getRequestHeaders().get("host").get(0);
        httpExchange.getResponseHeaders().set("Location", "http://" + hostPort + dest);http://
        httpExchange.sendResponseHeaders(301, -1);
    }

}