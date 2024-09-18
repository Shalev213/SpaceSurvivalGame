package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LevelOne extends AbstractLevel implements KeyListener {
    private Spaceship spaceship1;
    private Spaceship spaceship2;
    private Fuel fuel;
    private Stone stone1;
    private Stone stone2;
    private Stone stone3;


    public LevelOne(int width, int height, String teamName) {
        // אתחול הרקעים קודם לקריאה לבנאי של המחלקה האבסטרקטית
        this.spaceBackgroundOne = new ImageIcon("src/main/java/sources/LevelOne.png");
        this.spaceBackgroundTwo = new ImageIcon("src/main/java/sources/LevelOneMirror.png");

        // קריאה לבנאי של המחלקה האבסטרקטית אחרי אתחול הרקעים
        super.width = width;
        super.height = height;
        super.xOfBackgroundTwo = this.spaceBackgroundOne.getIconWidth();

        this.spaceship1 = new Spaceship("src/main/java/sources/Spaceship1.png");
        this.spaceship1.setY(200);
        this.spaceship2 = new Spaceship("src/main/java/sources/Spaceship2.png");
        this.spaceship2.setY(500);

        this.fuel = new Fuel();
        this.fuel.setRandomX(this.width, this.width * 2);
        this.fuel.setRandomY(0, this.height - this.fuel.getHeight());
        this.fuel.start();

        this.stone1 = new Stone("src/main/java/sources/stone1.png");
        this.stone1.setRandomX(this.width, this.width * 2);
        this.stone1.setRandomY( 0, this.height - this.stone1.getHeight());
        this.stone1.start();

        this.stone2 = new Stone("src/main/java/sources/stone2.png");
        this.stone2.setRandomX(this.width, this.width * 2);
        this.stone2.setRandomY( 0, this.height - this.stone2.getHeight());
        this.stone2.start();

        this.stone3 = new Stone("src/main/java/sources/stone3.png");
        this.stone3.setRandomX(this.width, this.width * 2);
        this.stone3.setRandomY( 0, this.height - this.stone3.getHeight());
        this.stone3.start();


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

//    @Override
    public void gameScene() {

        // Handle game logic here
        // You can send messages to the server using gameClient.sendMessage(message);
    }


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (spaceBackgroundOne != null && spaceBackgroundTwo != null) {
            this.spaceBackgroundOne.paintIcon(null, graphics, xOfBackgroundOne, 0);
            this.spaceBackgroundTwo.paintIcon(null, graphics, xOfBackgroundTwo, 0);
        }
        spaceship1.paintSpaceship(graphics);
        spaceship2.paintSpaceship(graphics);
        fuel.paintFuel(graphics);
        stone1.paintStone(graphics);
        stone2.paintStone(graphics);
        stone3.paintStone(graphics);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (this.spaceship1.getY() <= (height - 1.5 * spaceship1.getHeight() )) {
                spaceship1.upDownMove(4);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (this.spaceship1.getY() >= 0) {
                spaceship1.upDownMove(-4);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (this.spaceship1.getX() <= (this.width - 1.2 * spaceship1.getWidth() )) {
                spaceship1.leftRightMove(4);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (this.spaceship1.getX() >= 0) {
                spaceship1.leftRightMove(-4);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            if (this.spaceship2.getY() <= (height - 1.75 * spaceship2.getHeight())) {
                spaceship2.upDownMove(4);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (this.spaceship2.getY() >= 0) {
                spaceship2.upDownMove(-4);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            if (this.spaceship2.getX() <= (this.width - 1.2 * spaceship2.getWidth() )) {
                spaceship2.leftRightMove(4);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            if (this.spaceship2.getX() >= 0) {
                spaceship2.leftRightMove(-4);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
