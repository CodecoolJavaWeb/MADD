package com.codecool.queststore.controller;

import com.codecool.queststore.DAO.QuestDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class QuestController implements HttpHandler {

    private QuestDAO questDAO;

    public QuestController() {
        questDAO = new QuestDAO();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String response = "";

        if (method.equals("POST") && parsePath(httpExchange)[3].equals("create-quest")) {
            Map<String, String> questData = parseInputs(httpExchange);
            questDAO.addQuest(questData);
            JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/mentor-quests.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("questList", questDAO.getQuests());
            response = template.render(model);
            httpRedirectTo("/mentors/mentor-quests", httpExchange);
            httpExchange.sendResponseHeaders(200, response.length());
        }

        if (method.equals("GET") && parsePath(httpExchange).length <=3) {
            System.out.println("pre entered");
            JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/mentor-quests.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("questList", questDAO.getQuests());
            response = template.render(model);
            httpExchange.sendResponseHeaders(200, response.length());
        }
        else if (method.equals("GET") && parsePath(httpExchange)[3].equals("create-quest")) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/create-quest.twig");
            JtwigModel model = JtwigModel.newModel();
            response = template.render(model);
        }
        httpExchange.sendResponseHeaders(200, response.length());
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
