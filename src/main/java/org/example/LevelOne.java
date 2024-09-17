package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LevelOne extends AbstractLevel implements KeyListener {
    private final Spaceship spaceship1;
    private final Spaceship spaceship2;

    public LevelOne(int width, int height, String teamName) {
        // אתחול הרקעים קודם לקריאה לבנאי של המחלקה האבסטרקטית
        this.spaceBackgroundOne = new ImageIcon("src/main/java/sources/LevelOne.png");
        this.spaceBackgroundTwo = new ImageIcon("src/main/java/sources/LevelOneMirror.png");

        // קריאה לבנאי של המחלקה האבסטרקטית אחרי אתחול הרקעים
        super.width = width;
        super.height = height;
        super.xOfBackgroundTwo = this.spaceBackgroundOne.getIconWidth();

        this.spaceship1 = new Spaceship("src/main/java/sources/Spaceship1.png");
        this.spaceship1.setY(100);
        this.spaceship2 = new Spaceship("src/main/java/sources/Spaceship2.png");
        this.spaceship2.setY(300);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
        this.requestFocusInWindow();
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


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        spaceship1.paintSpaceship(graphics);
        spaceship2.paintSpaceship(graphics);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (this.spaceship1.getY() <= (height - 1.5 * spaceship1.getHeight() )) {
                spaceship1.move(3);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (this.spaceship1.getY() >= 0) {
                spaceship1.move(-3);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            if (this.spaceship2.getY() <= (height - 1.75 * spaceship2.getHeight())) {
                spaceship2.move(3);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (this.spaceship2.getY() >= 0) {
                spaceship2.move(-3);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
