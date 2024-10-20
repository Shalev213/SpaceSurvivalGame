package org.example;

import javax.swing.*;
import java.awt.*;



public class LevelButton extends JButton {
    public LevelButton(int x, int y) {
//        this.setText(text);
        this.setBounds(x, y, 150, 80);
//        this.setFont(new Font("Arial", Font.BOLD, 30));
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }

}
