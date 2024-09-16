package org.example;

import java.io.*;
import java.net.Socket;

public class GameClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean gameStarted = false;

    public GameClient(String serverAddress, int serverPort) throws IOException {
        socket = new Socket(serverAddress, serverPort);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void startListening() {
        new Thread(() -> {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received: " + message);
                    if (message.equals("START_GAME")) {
                        gameStarted = true;
                        startGame();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    private void startGame() {
        // Logic to start the game
        System.out.println("Game started!");
        // You can add more logic here to start the game
    }

    public boolean isGameStarted() {
        return gameStarted;
    }
}
