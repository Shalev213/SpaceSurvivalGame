package org.example;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket clientSocket;
    private GameServer server;
    private PrintWriter out;

    public ClientHandler(Socket socket, GameServer server) {
        this.clientSocket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String input;
            while ((input = in.readLine()) != null) {
                System.out.println("Received: " + input);
                server.broadcast(input, this); // לשדר את ההודעה לשחקן השני
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message); // שליחת הודעה ללקוח
        }
    }
}
