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

public class MentorController implements HttpHandler {

    private MentorDAO mentorDAO;
    private Map<String, String> formData;

    public MentorController() {
        mentorDAO = new MentorDAO();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String response;

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "UTF8");
            BufferedReader br = new BufferedReader(isr);
            String inputs = br.readLine();

            //read inputs into formData map.
            parseInputs(inputs);

        }
        if (method.equals("GET")) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/mentor-students.twig");
            JtwigModel model = JtwigModel.newModel();
            response = template.render(model);
            httpExchange.sendResponseHeaders(200, response.length());

            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    private void parseInputs (String inputs) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = inputs.split("&");
        for (String element : pairs) {
            String[] keyValue = element.split("=");
            String value = URLDecoder.decode(keyValue[1], "UTF8");
            map.put(keyValue[0], value);
        }
        formData = map;
    }
}
