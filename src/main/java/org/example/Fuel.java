package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Fuel extends Thread{

    private final Random random;
    private Image fuelImage;

    private final short width = 50;
    private final short height = 70;
    private String imagePath = "src/main/java/sources/Fuel.png";
    private int x = 200;
    private int y = 300;
    private int dx = 1;
    private boolean isAlive = true;


    public Fuel() {
        this.random = new Random();
//        this.x = random.nextInt(350, 600);
//        this.y = random.nextInt(0, 620);
        this.fuelImage = new ImageIcon(imagePath).getImage();




    }

    public void paintFuel(Graphics graphics){
//        System.out.println("x: " + x + ",y: " + y);
        graphics.drawImage(this.fuelImage, this.x, this.y, this.width, this.height, null);

    }

    public void run(){
        while (this.isAlive){
            this.move();


            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public short getHeight() {
        return height;
    }

    public void move() {
        this.x -= dx;
    }
    public void setRandomX(int origin, int bound) {
        this.x = random.nextInt(origin, bound);
    }
    public void setRandomY(int origin, int bound) {
        this.y = random.nextInt(origin, bound);
    }

}
