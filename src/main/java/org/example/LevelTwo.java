package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LevelTwo extends AbstractLevel implements KeyListener {
    private ImageIcon spaceshipBackground;
    private Astronaut astronaut;
    private int xOfBackground = 0;
    private boolean rightPressed = false;
    private boolean leftPressed = false;


    public LevelTwo(int width, int height) {
        // אתחול הרקעים קודם לקריאה לבנאי של המחלקה האבסטרקטית
        this.spaceBackgroundOne = null;
        this.spaceBackgroundTwo = null;
        this.spaceshipBackground = new ImageIcon("src/main/java/resources/levelTwoBackground.png");
        this.xOfBackground = -(this.spaceshipBackground.getIconWidth() - this.windowWidth) / 2;

        this.astronaut = new Astronaut();
        this.astronaut.setX((int) ((windowWidth - this.astronaut.getWidth()) / 2));
        this.astronaut.setY(((windowHeight - this.astronaut.getHeight()) / 2) + 60);

        // קריאה לבנאי של המחלקה האבסטרקטית אחרי אתחול הרקעים
        super.windowWidth = width;
        super.windowHeight = height;


        this.setFocusable(true);
        this.addKeyListener(this);
        this.requestFocus();
        this.requestFocusInWindow();
        mainGameLoop();
    }
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (spaceshipBackground != null) {
            this.spaceshipBackground.paintIcon(null, graphics, xOfBackground, 0);
        }
        astronaut.paintAstronaut(graphics);
    }

    @Override
    public int getBackgroundWidth() {
        return spaceshipBackground.getIconWidth();
    }

    @Override
    public void gameScene() {
        if (rightPressed && (this.xOfBackground + this.getBackgroundWidth()) > this.windowWidth ) {
            takeBackgroundLeft();
        }
        if (leftPressed && this.xOfBackground < 0) {
            takeBackgroundRight();
        }
        repaint();
    }

    @Override
    public void gameOver() {

    }
    public void takeBackgroundRight() {
        this.xOfBackground += 1;
    }
    public void takeBackgroundLeft() {
        this.xOfBackground -= 1;
    }

//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            System.out.println("right");
////            this.rightPressed = true; // עדכון הדגל
//        }
//        if (e.getKeyCode() == KeyEvent.VK_LEFT){
//            System.out.println("left");
////            this.leftPressed = true; // עדכון הדגל
//        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//
//    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT -> {
                this.rightPressed = true;
                this.astronaut.setMirrorChosen(false);
            }
            case KeyEvent.VK_LEFT -> {
                this.leftPressed = true;
                this.astronaut.setMirrorChosen(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT -> this.rightPressed = false;
            case KeyEvent.VK_LEFT -> this.leftPressed = false;
        }
    }
}