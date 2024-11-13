package org.example;

import javax.swing.*;
import java.awt.*;

public class FinalPanel extends JPanel {
    private final ImageIcon lobbyBackground;
//    private GifPanel gifPanel;
    private ImageIcon gifIcon;
    private JLabel gifLabel;


    public FinalPanel(int width, int height){
        this.lobbyBackground = new ImageIcon("src/main/java/resources/final_background.png");
        this.setSize(width, height);
        this.setLayout(null);
        this.setVisible(false);
        this.gifIcon = new ImageIcon("src/main/java/resources/final_video3.gif");
        gifLabel = new JLabel(gifIcon);
        gifLabel.setBounds(317,91,485,305);
        this.add(gifLabel);
//        this.add(gifIcon);

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (lobbyBackground != null){
            this.lobbyBackground.paintIcon(null, graphics, 0, 0);
        }

//        this.gifIcon.paintIcon(null,graphics, 300 , 90);
    }

    // הפעלת האנימציה
    public void start() {
        this.setVisible(true);
        gifLabel.setVisible(true);
    }

    // עצירת האנימציה
    public void stop() {
        gifLabel.setVisible(false);
        this.setVisible(false);
    }
}

