package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Alien extends Thread {

    private final Random random;
    private Image alien;
    private final short width = 50;
    private final short height = 70;
    private int x = 20;
    private int y = 50;
    private int dx = 1;
    private boolean isAlive = true;



    public Alien(String imagePath){
        this.random = new Random();
//        this.x = random.nextInt(origin, bound);
//        this.y = (short) (Window.getHeight());
//        this.imagePath = imagePath;
        alien = new ImageIcon(imagePath).getImage();

    }


    public void paintAlien(Graphics graphics){
//        System.out.println("Stone-> " + "x: " + x + ",y: " + y);
        graphics.drawImage(this.alien, this.x, this.y, this.width, this.height, null);

    }


    public void run() {

        while (this.isAlive) {
            this.move();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void move() {
        this.x -= dx;
    }

    public short getHeight() {
        return height;
    }

    public short getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public void setRandomX(int origin, int bound) {
        this.x = random.nextInt(origin, bound);
    }
    public void setRandomY(int origin, int bound) {
        this.y = random.nextInt(origin,bound);
    }

    public Rectangle rectangle() {
        return new Rectangle (this.x, this.y + 10 , this.width - 15 , this.height - 20);
    }




}
