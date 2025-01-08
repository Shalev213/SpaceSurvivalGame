package org.example;

import javax.swing.*;
import java.awt.*;

public class Spaceship {

    private Image spaceship;

    private final short width = 150;
    private final short height = 70;
    private double y = 0;
    private double x = 40;


    public Spaceship(String imagePath) {
        this.spaceship = new ImageIcon(imagePath).getImage();
    }

    public void paintSpaceship(Graphics graphics) {
        graphics.drawImage(this.spaceship, (int) this.x, (int) this.y, this.width, this.height, null);

    }

    public void setY(int y) {
        this.y = y;
    }

    public short getX() {
        return (short) this.x;
    }

    public short getY() {
        return (short) y;
    }

    public short getHeight() {
        return height;
    }

    public void upDownMove(double dy) {
        this.y += dy;
    }

    public void leftRightMove(int dx) {
        this.x += dx;
    }

    public short getWidth() {
        return this.width;
    }

    public Rectangle rectangle() {
        return new Rectangle((int) this.x, (int) (this.y + 8), this.width - 5, this.height - 16);
    }
}
