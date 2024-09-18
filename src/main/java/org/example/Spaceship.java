package org.example;

import javax.swing.*;
import java.awt.*;

public class Spaceship {

    private Image spaceship;

    private final short width = 150;
    private final short height = 70;
    private String imagePath;
    private int y = 0;
    private int x = 40;


    public Spaceship(String imagePath){
        this.imagePath = imagePath;
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
}
