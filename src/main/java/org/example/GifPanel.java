package org.example;

import javax.swing.*;
import java.awt.*;

public class GifPanel extends JPanel {

    private JLabel gifLabel;

    public GifPanel(String gifPath, int width, int height) {
        // יצירת ImageIcon עם ה-GIF
        ImageIcon gifIcon = new ImageIcon(gifPath);

        // יצירת JLabel עם ה-GIF
        gifLabel = new JLabel(gifIcon);

        // קביעת גודל הפאנל
        this.setPreferredSize(new Dimension(width, height));

        // הוספת ה-JLabel לפאנל
        this.setLayout(new BorderLayout());
        this.add(gifLabel, BorderLayout.CENTER);
    }

    // הפעלת האנימציה
    public void start() {
        gifLabel.setVisible(true);
    }

    // עצירת האנימציה
    public void stop() {
        gifLabel.setVisible(false);
    }
}
