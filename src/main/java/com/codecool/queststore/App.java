package com.codecool.queststore;

import com.codecool.queststore.controller.*;

import com.codecool.queststore.controller.AdminController;

import com.codecool.queststore.controller.AuthenticationController;
import com.codecool.queststore.controller.StudentController;
import com.codecool.queststore.controller.StaticController;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;


public class App {
    public static void main(String[] args) throws Exception {
        AuthenticationController authenticationController;
        StoreController storeController;

        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);

        httpServer.createContext("/login", authenticationController = new AuthenticationController());
        httpServer.createContext("/static/", new StaticController());
        httpServer.createContext("/inventory", new StudentController(authenticationController));
        httpServer.createContext("/store", storeController = new StoreController(authenticationController));
        httpServer.createContext("/incubator", new IncubatorController(authenticationController));
        httpServer.createContext("/store/store-buy-group", new StoreBuyGroupController(authenticationController));
        httpServer.createContext("/store/store-buy-one", new StoreBuyOneController(authenticationController, storeController));
        httpServer.createContext("/transactionhistory", new TransactionController(authenticationController));
        httpServer.createContext("/static", new StaticController());
        httpServer.createContext("/mentors", new MentorController());
        httpServer.createContext("/mentors/mentor-quests", new QuestController());
        httpServer.createContext("/quest", new StudentQuestController(authenticationController));
        httpServer.createContext("/mentors", new MentorController());
        httpServer.createContext("/login", new AuthenticationController());
        httpServer.createContext("/admin/mentors", new AdminController());
        httpServer.createContext("/admin/class", new AdminController());
        httpServer.createContext("/admin/level", new AdminController());
        httpServer.setExecutor(null);
        httpServer.start();
    }
}
