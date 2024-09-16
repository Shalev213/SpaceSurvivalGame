package org.example;

import org.example.ClientHandler;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameServer {
    private static final int PORT = 9806;
    private static List<ClientHandler> clients = new ArrayList<>();
    private static Map<String, Integer> teamCounts = new HashMap<>();
    private static Map<String, List<ClientHandler>> teamClients = new HashMap<>();

    public static void startServer() {
        new Thread(() -> {
            try {
                System.out.println("Waiting for clients...");
                ServerSocket ss = new ServerSocket(PORT);

                while (true) {
                    Socket soc = ss.accept();
                    System.out.println("Connection established");

                    ClientHandler clientHandler = new ClientHandler(soc);
                    clients.add(clientHandler);
                    new Thread(clientHandler).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public static void incrementTeamCount(String teamName, ClientHandler clientHandler) {
        teamCounts.put(teamName, teamCounts.getOrDefault(teamName, 0) + 1);
        teamClients.computeIfAbsent(teamName, k -> new ArrayList<>()).add(clientHandler);

        if (teamCounts.get(teamName) == 2) {
            for (ClientHandler client : teamClients.get(teamName)) {
                client.sendMessage("START_GAME");
            }
        }
    }
}