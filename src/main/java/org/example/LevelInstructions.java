package org.example;

import javax.swing.*;
import java.awt.*;

public class LevelInstructions extends JPanel {
    private JLabel title;
    private JLabel bodyLabel;
    private String strBody;
    private String path;
    private ImageIcon screenshot;
    private ImageIcon background;
    private int imageX;
    private int imageY;
    private int imageWidth = 550;
    private int imageHeight = 375;
    private JButton startGameButton;
    private int currentLevel;
    private boolean isClicked = false;
    private int yTitle = 30;
    private int xTitle;
    private int titleWidth = 450;
    private int titleHeight = 60;
    private int xBody;
    private int bodyWidth = 800;
    private int space = 90;


//    private InstructionsButton instructionsButton;


    public LevelInstructions(int currentLevel, int windowWidth, int windowHeight) {//int windowWidth, int windowHeight, String title, String textBody, String path, int imageX, int imageY
        this.background = new ImageIcon("src/main/java/resources/instructionBackgrond.png");

        this.currentLevel = currentLevel;
        this.setSize(windowWidth, windowHeight);
        this.setLayout(null);
        this.setVisible(false);
        this.setBackground(new Color(0x1010CA));

        setPanelByLevel();

        this.xTitle = (windowWidth - titleWidth) / 2;

        this.title = new JLabel("Level " + this.currentLevel + " - Instructions");
        this.title.setForeground(new Color(31, 191, 230));
        this.title.setFont(new Font("Arial", Font.BOLD, 45));
        this.title.setBounds(this.xTitle, this.yTitle, this.titleWidth, this.titleHeight);
        this.add(this.title);

        this.xBody = (windowWidth - this.bodyWidth) / 2;

        this.bodyLabel = new JLabel();
        this.bodyLabel.setText(strBody);
        this.bodyLabel.setForeground(new Color(31, 191, 230));
        this.bodyLabel.setFont(new Font("SansSerif", Font.PLAIN, 25));
        this.bodyLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        this.bodyLabel.setBounds(this.xBody, this.yTitle + this.space, this.bodyWidth, 190);
        this.add(this.bodyLabel);

        this.screenshot = new ImageIcon(path);
        this.imageX = (windowWidth - imageWidth) / 2;
        this.imageY = 320;

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


    public void paintComponent(Graphics graphics) {
//        super.paint(graphics);
        if (this.background != null){
            this.background.paintIcon(null, graphics, 0, 0);
        }
        if (this.screenshot != null) {
            graphics.drawImage(this.screenshot.getImage(), this.imageX, this.imageY, this.imageWidth, this.imageHeight, null);

            Graphics2D g2d = (Graphics2D) graphics;
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(10));
            g2d.drawRect(this.imageX, this.imageY, this.imageWidth, this.imageHeight);
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
                strBody = "Messi the goat of level 5";
            }
        }
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void reset() {
        isClicked = false;
    }

}
