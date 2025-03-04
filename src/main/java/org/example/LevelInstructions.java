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
    private int space = 50;
    private JButton lobbyButton;


//    private InstructionsButton instructionsButton;


    public LevelInstructions(int currentLevel, int windowWidth, int windowHeight) {//int windowWidth, int windowHeight, String title, String textBody, String path, int imageX, int imageY
        this.background = new ImageIcon("src/main/java/resources/instructionBackgrond.png");

        this.currentLevel = currentLevel;
        this.setSize(windowWidth, windowHeight);
        this.setLayout(null);
        this.setVisible(false);
        this.setBackground(new Color(0x1010CA));


        this.xTitle = (windowWidth - titleWidth) / 2;

        this.title = new JLabel("Level " + this.currentLevel + " - Instructions");
        this.title.setForeground(new Color(31, 191, 230));
        this.title.setFont(new Font("Arial", Font.BOLD, 45));
        this.title.setBounds(this.xTitle, this.yTitle, this.titleWidth, this.titleHeight);
        this.add(this.title);

        this.xBody = (windowWidth - this.bodyWidth) / 2;

        this.bodyLabel = new JLabel();
        this.bodyLabel.setForeground(new Color(255, 255, 255));
        this.bodyLabel.setFont(new Font("SansSerif", Font.PLAIN, 25));
        this.bodyLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        this.bodyLabel.setBounds(this.xBody, this.yTitle + this.space, this.bodyWidth, 235);
        setPanelByLevel();
        this.bodyLabel.setText(strBody);
        this.add(this.bodyLabel);

        this.screenshot = new ImageIcon(path);
        this.imageX = (windowWidth - imageWidth) / 2;
        this.imageY = 320;

        this.lobbyButton = new JButton("Back to lobby");
        this.lobbyButton.setBounds(25, windowHeight - 100, 200, 60);
        this.lobbyButton.setFont(new Font("Arial", Font.BOLD, 20));
//        this.lobbyButton.setFocusPainted(false);
//        this.lobbyButton.setBackground(new Color(43, 45, 48));
//        this.lobbyButton.setForeground(Color.white);
        this.add(this.lobbyButton);

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
        if (this.background != null) {
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
                strBody = "<html>In this level, two players must work together to collect five fuel " +
                        "tanks while avoiding incoming meteors. Player 1 controls his spaceship using " +
                        "the Arrow Keys (Left, Right, Up, Down), while Player 2 moves using WASD (W - Up," +
                        "A - Left, S - Down, D - Right). The team has a total of three lives—hitting  " +
                        "meteors three times results in failure. To complete the level, players must successfully " +
                        "collect all five fuel tanks while coordinating their movements to avoid collisions. Good luck! </html>";
            }
            case 2 -> {
                path = "src/main/java/resources/level2instructions.png";
                strBody = "<html>In this level, players must find a hidden malfunction within the <br>" +
                        "spaceship’s system by clicking on the correct component. <br>" +
                        "Only Player 1 can move the astronaut left and right using the Arrow <br>" +
                        "Keys to navigate inside the spaceship. Once the malfunction is found, <br>" +
                        "the players must solve a riddle to progress to the next level. <br>" +
                        "Stay sharp and work together to uncover the issue! </html>";
            }
            case 3 -> {
                this.bodyLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
                path = "src/main/java/resources/level3instructions.png";
                strBody = "<html>In this level, players control their spaceships similarly to Level 1," +
                        "but they can only move up and down. Player 1 pilots the red spaceship using " +
                        "the Arrow Keys, while Player 2 pilots the yellow spaceship using W and S. " +
                        "Additionally, movement can be controlled by holding a green object in front " +
                        "of the camera—Player 1 moves up or down if the object is detected on the right" +
                        "side of the screen, while Player 2 moves up or down if it's detected on the left" +
                        "side. Players must shoot and destroy 15 alien spaceships before they get past—Player" +
                        "1 shoots with ENTER, and Player 2 shoots with SPACEBAR. The team has three lives," +
                        "which are lost if a player crashes into an alien spaceship or if an alien spaceship gets past them." +
                        "Stay focused and take down the enemies!";

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

    public JButton getLobbyButton() {
        return lobbyButton;
    }

}
