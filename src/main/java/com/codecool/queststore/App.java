package com.codecool.queststore;
import com.codecool.queststore.controller.*;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.sql.Connection;


public class App {
    public static void main(String[] args) throws Exception {
        AuthenticationController authenticationController;

        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);
        httpServer.createContext("/login", authenticationController = new AuthenticationController());
        httpServer.createContext("/static/", new StaticController());
//        httpServer.createContext("/mentors", new MentorController());
//        httpServer.createContext("/classes", new CodecoolClassController());
//        httpServer.createContext("/levels", new LevelController());
        httpServer.createContext("/store", new StoreController());
        httpServer.createContext("/codecooler", new StudentController(authenticationController));
        httpServer.createContext("/incubator", new IncubatorController());
        httpServer.createContext("/store-buy-group", new StoreBuyGroupController());
        httpServer.createContext("/store-buy-one", new StoreBuyOneController());
        httpServer.createContext("/transactionhistory", new TransactionController());
        httpServer.createContext("/quest", new QuestController());

        httpServer.setExecutor(null);
        httpServer.start();
    }
}
