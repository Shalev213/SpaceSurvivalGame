package org.example;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class ColorButton extends JButton {
    private int width = 35;
    private int height = 35;
    private int x = 10;
    private int y = 100;
    private String[] colors;

    public ColorButton(int x, int y) {
        this.x = x;
        this.y = y;

//        this.setFont(new Font("Arial", Font.BOLD, 25));
        this.setBounds(this.x, this.y, this.width,this.height);
//        this.returnButton.setFocusPainted(false);
//        this.returnButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

    }
    public void changeColor(){
//        this.setBackground(Color.);
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
