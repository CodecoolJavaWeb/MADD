package com.codecool.queststore;
import com.codecool.queststore.controller.AuthenticationController;
import com.codecool.queststore.controller.StudentController;

import com.codecool.queststore.controller.MentorController;
import com.codecool.queststore.controller.StaticController;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;


public class App {
    public static void main(String[] args) throws Exception {

        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);
        httpServer.createContext("/login", new AuthenticationController());
        httpServer.createContext("/static", new Static());
        httpServer.createContext("/static/", new StaticController());

//        httpServer.createContext("/mentors", new MentorController());
//        httpServer.createContext("/classes", new CodecoolClassController());
//        httpServer.createContext("/levels", new LevelController());
//        httpServer.createContext("/store", new StoreController());
//        httpServer.createContext("/students", new StudentController());
        httpServer.setExecutor(null);
        httpServer.start();
    }
}
