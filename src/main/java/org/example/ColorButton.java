package org.example;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.blue;

public class ColorButton extends JButton {
    private int width = 50;
    private int height = 50;
    private int x = 10;
    private int y = 450;
    private int index = 0;
    private  Color[] colors = {Color.BLUE, Color.BLACK, Color.RED, Color.GREEN, Color.YELLOW, Color.MAGENTA};
    public ColorButton(int x, int index) {
        this.x = x;
        this.index = index;
        this.changeColor();

//        this.setFont(new Font("Arial", Font.BOLD, 25));
        this.setBounds(this.x, this.y, this.width,this.height);
        this.addActionListener(e -> {
            changeColor();
        });
    }
    public void changeColor(){
        this.setBackground(colors[index % 6]);
        this.index++;
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
