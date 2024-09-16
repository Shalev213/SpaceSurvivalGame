package org.example;

import javax.swing.*;
import java.io.IOException;

public class LevelOne extends AbstractLevel {
    private GameClient gameClient;

    public LevelOne(int width, int height, String teamName) {
        // אתחול הרקעים קודם לקריאה לבנאי של המחלקה האבסטרקטית
        this.spaceBackgroundOne = new ImageIcon("src/main/java/sources/LevelOne.png");
        this.spaceBackgroundTwo = new ImageIcon("src/main/java/sources/LevelOneMirror.png");

        // קריאה לבנאי של המחלקה האבסטרקטית אחרי אתחול הרקעים
        super.width = width;
        super.height = height;
        super.xOfBackgroundTwo = this.spaceBackgroundOne.getIconWidth();

        try {
            gameClient = new GameClient("localhost", 9806);
            gameClient.startListening();
            gameClient.sendMessage("TEAM:" + teamName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainGameLoop();
    }

    @Override
    public int getBackgroundWidth() {
        return spaceBackgroundOne.getIconWidth();
    }

    @Override
    public void gameScene() {
        // Handle game logic here
        // You can send messages to the server using gameClient.sendMessage(message);
    }
}
