package org.example;

import javax.swing.*;
import java.awt.*;

public class LevelInstructions extends JPanel {
    private JLabel title;
    private JLabel bodyLabel;
    private String strBody;
    private String path;
    private ImageIcon screenshot;
    private int imageX;
    private int imageY;
    private JButton startGameButton;
    private int currentLevel;
    private boolean isClicked = false;

//    private InstructionsButton instructionsButton;


    public LevelInstructions(int currentLevel, int windowWidth, int windowHeight) {//int windowWidth, int windowHeight, String title, String textBody, String path, int imageX, int imageY
        this.currentLevel = currentLevel;
        this.setSize(windowWidth, windowHeight);
        this.setLayout(null);
        this.setVisible(false);
        this.setBackground(new Color(0x1010CA));

        setPanelByLevel();

        this.title = new JLabel("Level " + this.currentLevel + " - Instructions");
        this.title.setForeground(new Color(31, 191, 230));
        this.title.setFont(new Font("Arial", Font.BOLD, 60));
        this.title.setBounds(250, 100, 350, 150);
        this.add(this.title);

        this.bodyLabel = new JLabel();
        this.bodyLabel.setText(strBody);
        this.bodyLabel.setForeground(new Color(31, 191, 230));
        this.bodyLabel.setFont(new Font("Arial", Font.BOLD, 60));
        this.bodyLabel.setBounds(10, 200, 550, 800);
        this.add(this.bodyLabel);

        this.screenshot = new ImageIcon(path);
        this.imageX = 100;
        this.imageY = 400;

//        this.startGameButton = new JButton("Start");
//        this.startGameButton.setFont(new Font("Arial", Font.BOLD, 25));
//        this.startGameButton.setBounds(imageX + 200, imageY + 100, imageX / 2, 50);
//
//        this.startGameButton.addActionListener(e -> {
//            isClicked = true;
//            System.out.println("button clicked");
////            remove(this);
//            setVisible(false);
//        });
//        this.add(this.startGameButton);
    }


    public void paintInstructions(Graphics graphics) {
        super.paint(graphics);
        if (this.screenshot != null) {
            this.screenshot.paintIcon(null, graphics, this.imageX, this.imageY);
        }
    }


    public void setPanelByLevel() {

        switch (currentLevel) {
            case 1 -> {
                path = "src/main/java/resources/level1instructions.png";
                strBody = "111111111111111111111111";
            }
            case 2 -> {
                path = "src/main/java/resources/level2instructions.png";
                strBody = "22222222222222222222222222";
            }
            case 3 -> {
                path = "src/main/java/resources/level3instructions.png";
                strBody = "3333333333333333333333333333";
            }
            case 4 -> {
                path = "src/main/java/resources/level2instructions.png";
                strBody = "4444444444444444444444444444";
            }
            case 5 -> {
                path = "src/main/java/resources/level5instructions.png";
                strBody = "5555555555555555555555555555555";
            }
        }
    }


//    public String levelTwoBody() {
//        String sade = "adasd";
//        return sade;
//    }
//
//    public String levelThreeBody() {
//        String sade = "adasd";
//        return sade;
//    }
//
//    public String levelFourBody() {
//        String sade = "adasd";
//        return sade;
//    }
//
//    public String levelFiveBody() {
//        String sade = "adasd";
//        return sade;
//    }


    public boolean isClicked() {
        return isClicked;
    }

    public void reset() {
        isClicked = false;
    }

}
