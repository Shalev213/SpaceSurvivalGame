package org.example;

import javax.swing.*;
import java.awt.*;


public class Laser {
    private short x;
    private short y;
    private final byte width = 50;
    private final byte height = 12;
    private Image laser;


    public Laser(String imagePath){
        this.laser = new ImageIcon(imagePath).getImage();
    }

    public void paintLaser(Graphics graphics) {
        graphics.drawImage(this.laser,  this.x,  this.y , width, height,null);
    }

    public void fire() {
        this.x += 4;
    }

    public Rectangle rectangle() {
        return new Rectangle(this.x, this.y, this.width , this.height);
    }

    public void setX(short x) {
        this.x = x;
    }
    public void setY(short y) {
        this.y = y;
    }

    public short getX() {
        return x;
    }

    public short getY() {
        return y;
    }
}
