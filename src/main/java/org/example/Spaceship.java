package org.example;

import javax.swing.*;
import java.awt.*;

public class Spaceship {

    private Image spaceship;

    private final short width = 150;
    private final short height = 70;
    private int y = 0;
    private int x = 40;


    public Spaceship(String imagePath){
        this.spaceship = new ImageIcon(imagePath).getImage();
    }

    public void paintSpaceship(Graphics graphics){
        graphics.drawImage(this.spaceship,this.x, this.y, this.width, this.height, null);

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

    public void upDownMove(int dy) {
        this.y += dy;
    }
    public void leftRightMove(int dx) {
        this.x += dx;
    }

    public double getX() {
        return this.x;
    }

    public double getWidth() {
        return this.width;
    }

    public Rectangle rectangle() {
        return new Rectangle(this.x, this.y + 8 , this.width - 5 , this.height - 16);
    }
}
