package com.codecool.queststore.controller;

import com.codecool.queststore.DAO.StoreDAO;
import com.codecool.queststore.DAO.StudentDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class StoreController implements HttpHandler {

    private AuthenticationController authenticationController;
    private StudentDAO studentDAO;
    private StoreDAO storeDAO;
    private StoreBuyOneController storeBuyOneController;
    private Map<String, String> map;


    public StoreController(AuthenticationController authenticationController) {
        this.authenticationController = authenticationController;
        this.studentDAO = new StudentDAO();
        this.storeDAO = new StoreDAO();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        System.out.println("method " + method);
        String response = "";
        System.out.println("HERE Store");


        if (method.equals("GET")) {

            int userID = getUserID();
            int studentID = getStudentID(userID);

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/store.twig");
            JtwigModel model = JtwigModel.newModel();

            model.with("artifacts", storeDAO.getStudentArtifactList());
            model.with("userName",  studentDAO.getStudentName(userID));
            response = template.render(model);
        }
        if(method.equals("POST")){
            add(httpExchange);
            httpRedirectTo("/store-buy-one", httpExchange);
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
        this.map = parseInputs(inputs);
        return map;
    }
    private static Map<String, String> parseInputs(String inputs) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String [] pairs = inputs.split("&");
        for (String element : pairs) {
            String [] keyValue = element.split("=");
            String value = URLDecoder.decode(keyValue[1], "UTF8");
            map.put(value, keyValue[0]);
        }
        return map;
    }
    private void httpRedirectTo(String dest, HttpExchange httpExchange) throws IOException {
        String hostPort = httpExchange.getRequestHeaders().get("host").get(0);
        httpExchange.getResponseHeaders().set("Location", "http://" + hostPort + dest);
        httpExchange.sendResponseHeaders(301, -1);
    }
}
