package org.example;

import javax.swing.*;
import java.awt.*;

public class LevelInstructions extends JPanel {
    private JLabel title;
    private JLabel textBody;
    private ImageIcon image;
    private int imageX;
    private int imageY;
    private JButton startGameButton;
    private boolean isClicked = false;
    private InstructionsButton instructionsButton;


    public LevelInstructions(int windowWidth, int windowHeight, String title, String textBody, String path, int imageX, int imageY) {
        this.setSize(windowWidth, windowHeight);
        this.setLayout(null);
        this.setBackground(new Color(0x1010CA));
        this.title = new JLabel(title);
        this.title.setForeground(new Color(31, 191, 230));
        this.title.setFont(new Font("Arial", Font.BOLD, 60));
        this.title.setBounds(150, 100, 350, 150);
        this.add(this.title);

        this.textBody = new JLabel();
        this.textBody.setText(textBody);
        this.textBody.setForeground(new Color(31, 191, 230));
        this.textBody.setFont(new Font("Arial", Font.BOLD, 60));
        this.textBody.setBounds(10, 200, 550, 800);
        this.add(this.textBody);

        this.image = new ImageIcon(path);
        this.imageX = imageX;
        this.imageY = imageY;

        this.startGameButton = new JButton("Start");
        this.startGameButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.startGameButton.setBounds(imageX + 200, imageY + 100, imageX / 2, 50);

        this.startGameButton.addActionListener(e -> {
            isClicked = true;
            System.out.println("button clicked");
//            remove(this);
            setVisible(false);
        });
        this.add(this.startGameButton);
    }


    public void paintInstructions(Graphics graphics) {
        super.paint(graphics);
        this.image.paintIcon(null, graphics, this.imageX, this.imageY);
    }

    public boolean isClicked() {
        return isClicked;
    }
    public void reset() {
        isClicked = false;
    }

}
