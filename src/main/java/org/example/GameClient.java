package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {

    public GameClient() {
        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            // תזוזת השחקן שלך
            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String movement = scanner.nextLine(); // למשל: "UP", "DOWN", "LEFT", "RIGHT"
                    out.println(movement); // שדר את התזוזה לשרת
                }
            }).start();

            // קבלת עדכון מהשרת על השחקן השני
            String serverMessage;
            while ((serverMessage = in.readLine()) != null) {
                System.out.println("Server says: " + serverMessage); // עיבוד התזוזה של השחקן השני
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GameClient();
    }
}
