package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;



public class LevelButton extends JButton {
    public LevelButton(int x, int y) {
//        this.setText(text);
        this.setBounds(x, y, 150, 80);  // גודל קטן יותר של הכפתור
        this.setFont(new Font("Arial", Font.BOLD, 30));  // פונט קטן יותר
        this.setFocusPainted(false);  // לא לצבוע את המסגרת כשהכפתור מקבל פוקוס
        this.setOpaque(false);  // להפוך את הכפתור לשקוף
        this.setContentAreaFilled(false);  // להסיר את מילוי האזור של הכפתור
        this.setBorderPainted(false);  // לא לצייר את הגבול של הכפתור
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // צייר את הרכיב כפי שהוא בדרך כלל
    }

    @Override
    protected void paintBorder(Graphics g) {
        // אם אתה עדיין רוצה גבול, אתה יכול להשאיר את זה, אחרת אפשר להסיר את זה:
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }

//    @Override
////    public boolean contains(int x, int y) {
////        if ( == null || !shape.getBounds().equals(getBounds())) {
////            shape = new Ellipse2D.Float(0, 0, getSize().width - 1, getSize().height - 1);
////        }
////        return shape.contains(x, y);
////    }
}
