package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {

    private List<ClientHandler> players = new ArrayList<>();

    public GameServer() {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is running...");

            while (players.size() < 2) { // חיבור של שני שחקנים בלבד
                Socket clientSocket = serverSocket.accept();
                ClientHandler player = new ClientHandler(clientSocket, this);
                players.add(player);
                player.start();
                System.out.println("Player " + players.size() + " connected.");
            }

            // כאשר שני השחקנים מחוברים, להתחיל את המשחק
            if (players.size() == 2) {
                System.out.println("Both players connected. Starting the game.");
                startGame();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String message, ClientHandler sender) {
        for (ClientHandler player : players) {
            if (player != sender) {
                player.sendMessage(message); // שלח את ההודעה לשחקן השני
            }
        }
    }

    private void startGame() {
        // התחלת המשחק בין שני השחקנים
        for (ClientHandler player : players) {
            player.sendMessage("Game has started!");
        }
    }

}
