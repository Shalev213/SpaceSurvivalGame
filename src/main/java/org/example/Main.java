package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        try {
//            GameClient client = new GameClient("localhost", 9806);
//            client.startListening();
//
//            // Send a test message
//            client.sendMessage("Hello from client!");
//
//            // Keep the client running
//            while (true) {
//                // You can add more logic here if needed
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        GameServer.startServer();


        Window window = new Window();
        window.showWindow();

    }
}