package org.example;

import javax.swing.*;
import java.awt.*;

public class FinalPanel extends JPanel {
    private final ImageIcon lobbyBackground;
    private ImageIcon gifIcon;
    private JLabel gifLabel;
    private JButton lobbyButton;
    private int width;
    private int height;
    private int lobbyButtonWidth;
    private int lobbyButtonHeight;



    public FinalPanel(int width, int height){

        this.lobbyBackground = new ImageIcon("src/main/java/resources/final_background.png");
        this.setSize(width, height);
        this.setLayout(null);
        this.setVisible(false);
        this.gifIcon = new ImageIcon("src/main/java/resources/final_video3.gif");
        gifLabel = new JLabel(gifIcon);
        gifLabel.setBounds(317,91,485,305);
        this.add(gifLabel);

        this.lobbyButton = new JButton("Back to lobby");
        this.lobbyButton.setBounds(320, 85, 480, 315);
        this.lobbyButton.setFont(new Font("Georgia", Font.BOLD, 60));
        this.lobbyButton.setFocusPainted(false);
        this.lobbyButton.setOpaque(true);
        this.lobbyButton.setVisible(false);


    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (lobbyBackground != null){
            this.lobbyBackground.paintIcon(null, graphics, 0, 0);
        }
    }

    // הפעלת האנימציה
    public void start() {
        this.setVisible(true);
        gifLabel.setVisible(true);
    }

    // עצירת האנימציה
    public void stop() {
        gifLabel.setVisible(false);

//        this.setVisible(false);
        this.add(lobbyButton);
    }
    public void showLobbyButton() {
        this.lobbyButton.setVisible(true);
    }

    public JButton getLobbyButton() {
        return lobbyButton;
    }
}

