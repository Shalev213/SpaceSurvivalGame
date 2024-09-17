package org.example;

import javax.swing.*;
import java.awt.*;

public class Spaceship {

    private Image spaceship;

    private final short width = 150;
    private final short height = 70;
    private String imagePath;
    private int y = 0;


    public Spaceship(String imagePath){
        this.imagePath = imagePath;
        this.spaceship = new ImageIcon(imagePath).getImage();




    }

    public void paintSpaceship(Graphics graphics){
        graphics.drawImage(this.spaceship,25, this.y, this.width, this.height, null);

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

    public void move(int dy) {
        this.y += dy;
    }
}
