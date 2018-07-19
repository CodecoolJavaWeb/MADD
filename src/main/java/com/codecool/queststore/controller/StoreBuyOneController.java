package com.codecool.queststore.controller;

import com.codecool.queststore.ConnectionProvider;
import com.codecool.queststore.DAO.StoreDAO;
import com.codecool.queststore.DAO.StudentArtifactDAO;
import com.codecool.queststore.DAO.StudentDAO;
import com.codecool.queststore.model.Artifact;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreBuyOneController implements HttpHandler {

    private AuthenticationController authenticationController;
    private StudentDAO studentDAO;
    private Connection connection = new ConnectionProvider().getConnection();
    private StoreController storeController;
    private StudentArtifactDAO studentArtifactDAO;

    public StoreBuyOneController(AuthenticationController authenticationController, StoreController storeController){
        this.authenticationController = authenticationController;
        this.studentDAO = new StudentDAO();
        this.storeController = storeController;
        this.studentArtifactDAO = new StudentArtifactDAO();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        System.out.println("method " + method);
        String response = "";
        System.out.println("HERE storebyone");
        int itemID = Integer.parseInt(storeController.getMap().get("BUY"));
        int userID = getUserID();
        int studentID = getStudentID(userID);

        if (method.equals("GET")) {

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/store-buy-one.twig");
            JtwigModel model = JtwigModel.newModel();

            model.with("artifacts", studentArtifactDAO.getArtifactByID(itemID));
            response = template.render(model);
        }

        if(method.equals("POST")){
            Map<String, String> map = add(httpExchange);
           new StoreDAO().addArtifactToStudent(studentID, itemID);
            httpRedirectTo("/inventory", httpExchange);

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
    private Map<String, String> add(HttpExchange httpExchange) throws IOException {

        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "UTF8");
        BufferedReader br = new BufferedReader(isr);
        String inputs = br.readLine();
        System.out.println(inputs);
        Map<String, String> map = parseInputs(inputs);
        return map;
    }
    private static Map<String, String> parseInputs(String inputs) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String [] pairs = inputs.split("&");
        for (String element : pairs) {
            String [] keyValue = element.split("=");
            String value = URLDecoder.decode(keyValue[1], "UTF8");
            map.put(value, keyValue[0]);
            System.out.println("mapa" + map);
        }
        return map;
    }
    private void httpRedirectTo(String dest, HttpExchange httpExchange) throws IOException {
        String hostPort = httpExchange.getRequestHeaders().get("host").get(0);
        httpExchange.getResponseHeaders().set("Location", "http://" + hostPort + dest);
        httpExchange.sendResponseHeaders(301, -1);

    }

}
