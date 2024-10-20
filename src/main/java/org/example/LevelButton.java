package org.example;

import javax.swing.*;
import java.awt.*;



public class LevelButton extends JButton {
    public LevelButton(int x, int y) {
//        this.setText(text);
        this.setFont(new Font("Arial", Font.BOLD, 70));
        this.setBounds(x, y, 160, 85);
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
//        this.setBackground(new Color(0, 0, 0, 100));

        this.setBorderPainted(false);


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

//    @Override
//    protected void paintBorder(Graphics g) {
////        g.setColor(getForeground());
//        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
//    }
    @Override
    protected void paintBorder(Graphics g) {
        // יצירת אובייקט Graphics2D לעבודה עם Stroke (עובי קו)
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getForeground());

        // הגדרת עובי הקו, למשל 5 פיקסלים
        g2.setStroke(new BasicStroke(5));

        // ציור העיגול עם עובי הקו החדש
        g2.drawOval(6, 6, getSize().width - 10, getSize().height - 10);
    }


    public void checkEnable() {
        if (this.isEnabled()){
//            this.setContentAreaFilled(true);
//            this.setBackground(new Color(0, 0, 0, 0));
            this.setText("");
            this.setForeground(Color.GREEN);

        }else {
            this.setText("X");
            this.setForeground(Color.RED);
        }
    }
}
