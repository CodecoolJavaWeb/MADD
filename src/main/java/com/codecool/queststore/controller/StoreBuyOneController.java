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
    private StoreDAO storeDAO;

    public StoreBuyOneController(AuthenticationController authenticationController, StoreController storeController){
        this.authenticationController = authenticationController;
        this.studentDAO = new StudentDAO();
        this.storeController = storeController;
        this.studentArtifactDAO = new StudentArtifactDAO();
        this.storeDAO = new StoreDAO();
    }
    public StoreBuyOneController(StoreController storeController){
        this.studentDAO = new StudentDAO();
        this.storeController = storeController;
        this.studentArtifactDAO = new StudentArtifactDAO();
        this.storeDAO = new StoreDAO();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String response = "";
        int itemID = Integer.parseInt(storeController.getMap().get("BUY"));
        int userID = getUserID();
        int studentID = getStudentID(userID);

        if (method.equals("GET")) {

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/store-buy-one.twig");
            JtwigModel model = JtwigModel.newModel();
	        model.with("userName",  studentDAO.getStudentName(userID));
            model.with("studentMoney", studentDAO.getStudentMoney(studentID));
            model.with("artifacts", studentArtifactDAO.getArtifactByID(itemID));
            response = template.render(model);
        }

        if(method.equals("POST")){
            Map<String, String> map = add(httpExchange);  // To extends in future

            if(payForItem()){
                new StoreDAO().addArtifactToStudent(studentID, itemID);
                JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/codecooler.twig");
                JtwigModel model = JtwigModel.newModel();
                response = template.render(model);
                httpRedirectTo("/inventory", httpExchange);
            }
            else {
                JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student/codecooler.twig");
                JtwigModel model = JtwigModel.newModel();
                response = template.render(model);
                httpRedirectTo("/inventory", httpExchange);
            }

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
        }
        return map;
    }

    private void httpRedirectTo(String dest, HttpExchange httpExchange) throws IOException {
        String hostPort = httpExchange.getRequestHeaders().get("host").get(0);
        httpExchange.getResponseHeaders().set("Location", "http://" + hostPort + dest);
        httpExchange.sendResponseHeaders(301, -1);

    }

    public Boolean validatePay(){
        int studentID = storeController.getStudentID();
        int studentMoney = studentDAO.getStudentMoney(studentID);
        int itemID = Integer.parseInt(storeController.getMap().get("BUY"));

        for(Artifact artifact : storeDAO.getStudentArtifactList()){
            if(itemID == artifact.getId_artifact()){
                if(studentMoney > artifact.getPrice()){
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean payForItem(){

        int itemID = Integer.parseInt(storeController.getMap().get("BUY"));

        for(Artifact artifact : storeDAO.getStudentArtifactList()){
            if(itemID == artifact.getId_artifact()) {
                studentDAO.updateMoney(artifact.getPrice(), storeController.getStudentID());
                return true;
            }
        }
        return false;
    }
}
