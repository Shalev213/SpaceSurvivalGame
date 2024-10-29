package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Alien extends Thread {

    private final Random random;
    private Image alienToRight;
    private Image alienToLeft;
    private final short width = 180;
    private final short height = 260;
    private int x = 20;
    private int y;
    private int dx = 1;
//    private int mdx = -1;

    private boolean isAlive = true;

    private boolean isWalkingRight = true;




    public Alien(String leftImagePath, String rightImagePath){
        this.random = new Random();

        alienToRight = new ImageIcon(rightImagePath).getImage();
        alienToLeft = new ImageIcon(leftImagePath).getImage();

        this.y = 750 - this.height - 50;


    }


    public void paintAlien(Graphics graphics){
        if (isWalkingRight){
            graphics.drawImage(this.alienToRight, this.x, this.y, this.width, this.height, null);
        } else {
            graphics.drawImage(this.alienToLeft, this.x, this.y, this.width, this.height, null);
        }

    }


    public void run() {

        while (this.isAlive) {
            if (isWalkingRight){
                this.moveRight();
//                isWalkingRight = false;
            } else {
                this.moveLeft();
//                isWalkingRight = true;
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
//        if (isWalkingRight){
//            System.out.println("END THREAD AND SET RANDOM RIGHT");
//            setRandomX(-1500 ,-500);
//        } else {
//            System.out.println("END THREAD AND SET RANDOM LEFT");
//            setRandomX(1100 ,1100*2);
//        }
    }


    public void moveLeft() {
        this.x -= dx;
    }

    public void moveRight() {
        this.x += dx;
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

    public void setRandomX() {
        if (isWalkingRight){
            System.out.println("END THREAD AND SET RANDOM RIGHT");
            this.x = random.nextInt(-1500, -500);
        } else {
            System.out.println("END THREAD AND SET RANDOM LEFT");
            this.x = random.nextInt(1100, 1100 * 2);

        }
    }


    public Rectangle rectangle() {
        return new Rectangle (this.x, this.y + 10 , this.width - 15 , this.height - 20);
    }



    public void setWalkingRight(boolean walkingRight) {
        isWalkingRight = walkingRight;
    }

    public boolean isWalkingRight() {
        return isWalkingRight;
    }
}
