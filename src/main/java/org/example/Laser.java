package org.example;

import javax.swing.*;
import java.awt.*;


public class Laser {
    private int x;
    private int y;
    private final byte width = 50;
    private final byte height = 12;
    private Image laser;


    public Laser(String imagePath){
        this.laser = new ImageIcon(imagePath).getImage();
//        this.x = x;
//        this.y = y;
    }

    public void paintLaser(Graphics graphics) {
        graphics.drawImage(this.laser,  this.x,  this.y , width, height,null);
    }

    public void fire(Boolean isShootToRight,int speed) {
        if (isShootToRight) {
            this.x += speed;
        }
        else {
            this.x -= speed;
        }
    }

    public void fireDown() {
        this.y += 3;
    }

    public Rectangle rectangle() {
        return new Rectangle(this.x, this.y, this.width , this.height);
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public byte getWidth() {
        return width;
    }
}
