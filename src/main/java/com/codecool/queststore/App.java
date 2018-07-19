package com.codecool.queststore;

import com.codecool.queststore.controller.*;

import com.codecool.queststore.controller.AuthenticationController;
import com.codecool.queststore.controller.StudentController;

import com.codecool.queststore.controller.MentorController;
import com.codecool.queststore.controller.StaticController;

import com.codecool.queststore.controller.*;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;


public class App {
    public static void main(String[] args) throws Exception {
        AuthenticationController authenticationController;

        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);

        httpServer.createContext("/login", authenticationController = new AuthenticationController());
        httpServer.createContext("/static", new Static());
        httpServer.createContext("/static/", new StaticController());
//        httpServer.createContext("/mentors", new MentorController());
//        httpServer.createContext("/classes", new CodecoolClassController());
//        httpServer.createContext("/levels", new LevelController());
        httpServer.createContext("/store", new StoreController(authenticationController));
        httpServer.createContext("/codecooler", new StudentController(authenticationController));
        httpServer.createContext("/incubator", new IncubatorController(authenticationController));
        httpServer.createContext("/store-buy-group", new StoreBuyGroupController(authenticationController));
        httpServer.createContext("/store-buy-one", new StoreBuyOneController(authenticationController));
        httpServer.createContext("/transactionhistory", new TransactionController(authenticationController));
        httpServer.createContext("/quest", new QuestController(authenticationController));

        httpServer.setExecutor(null);
        httpServer.start();
    }
}
