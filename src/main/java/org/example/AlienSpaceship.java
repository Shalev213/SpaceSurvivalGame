package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AlienSpaceship extends Thread{
    private final Random random;
    private int firePoint;
    private Image alienSpaceship;
    //    private String imagePath;
    private final short width = 120;
    private final short height = 70;
    private int x = 20;
    private int y = 50;
    private int dx = 1;
    private boolean isAlive = true;


    public AlienSpaceship(String imagePath){
        this.random = new Random();
//        this.x = random.nextInt(origin, bound);
//        this.y = (short) (Window.getHeight());
//        this.imagePath = imagePath;
        alienSpaceship = new ImageIcon(imagePath).getImage();

    }


    public void paintAlienSpaceship(Graphics graphics){
        graphics.drawImage(this.alienSpaceship, this.x, this.y, this.width, this.height, null);

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


    public int randomFirePoint(int origin, int bound) {
        firePoint = random.nextInt(origin, bound);
        return firePoint;
    }

    public int getFirePoint() {
        return firePoint;

    }


    public Rectangle rectangle() {
        return new Rectangle (this.x, this.y + 10 , this.width - 15 , this.height - 20);
    }

    public int getY() {
        return y;
    }
}
