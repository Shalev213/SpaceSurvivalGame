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

    private String wavPath = " ";
    private JButton spokenButton;
    private Sound spokenSound;
    private JButton pauseButton;
    private boolean isPause = false;


//    private InstructionsButton instructionsButton;


    public LevelInstructions(int currentLevel, int windowWidth, int windowHeight) {//int windowWidth, int windowHeight, String title, String textBody, String path, int imageX, int imageY
        this.background = new ImageIcon("src/main/java/resources/instructionBackgrond.png");

        this.currentLevel = currentLevel;
        this.setSize(windowWidth, windowHeight);
        this.setLayout(null);
        this.setVisible(false);
        this.setBackground(new Color(0x1010CA));


        this.spokenButton = new JButton("Spoken Instruction");
        this.spokenButton.setBounds(windowWidth - 200, windowHeight - 120, 250, 60);
        this.spokenButton.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(this.spokenButton);

        this.pauseButton = new JButton("Pause");
        this.pauseButton.setBounds(windowWidth - 450, windowHeight - 120, 150, 60);
        this.pauseButton.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(this.pauseButton);
        pauseButton.setVisible(false);

        this.spokenSound = new Sound();


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
        this.add(this.bodyLabel);


        this.screenshot = new ImageIcon(path);
        this.imageX = (windowWidth - imageWidth) / 2;
        this.imageY = 320;

        this.lobbyButton = new JButton("Back to lobby");
        this.lobbyButton.setBounds(25, windowHeight - 120, 200, 60);
        this.lobbyButton.setFont(new Font("Arial", Font.BOLD, 20));
//        this.lobbyButton.setFocusPainted(false);
//        this.lobbyButton.setBackground(new Color(43, 45, 48));
//        this.lobbyButton.setForeground(Color.white);
        this.lobbyButton.addActionListener(e -> {
            this.spokenSound.stopPlay();
        });
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
                        "A - Left, S - Down, D - Right). The team has a total of three lives-hitting  " +
                        "meteors three times results in failure. To complete the level, players must successfully " +
                        "collect all five fuel tanks while coordinating their movements to avoid collisions. Good luck! </html>";
                wavPath = "src/main/java/resources/Level1Instructions.wav";
            }
            case 2 -> {
                this.bodyLabel.setFont(new Font("SansSerif", Font.PLAIN, 26));
                path = "src/main/java/resources/level2instructions.png";
                strBody = "<html>In this level, players must find a hidden malfunction within the <br>" +
                        "spaceship’s system by clicking on the correct component. <br>" +
                        "Only Player 1 can move the astronaut left and right using the Arrow <br>" +
                        "Keys to navigate inside the spaceship. Once the malfunction is found, <br>" +
                        "the players must solve a riddle to progress to the next level. <br>" +
                        "Stay sharp and work together to uncover the issue! </html>";
                wavPath = "src/main/java/resources/Level2Instructions.wav";
            }
            case 3 -> {
                this.bodyLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
                path = "src/main/java/resources/level3instructions.png";
                strBody = "<html>In this level, players control their spaceships similarly to Level 1," +
                        "but they can only move up and down. Player 1 pilots the red spaceship using " +
                        "the Arrow Keys, while Player 2 pilots the yellow spaceship using W and S. " +
                        "Additionally, movement can be controlled by holding a green object in front " +
                        "of the camera-Player 1 moves up or down if the object is detected on the right" +
                        "side of the screen, while Player 2 moves up or down if it's detected on the left" +
                        "side. Players must shoot and destroy 15 alien spaceships before they get past-Player" +
                        "1 shoots with ENTER, and Player 2 shoots with SPACEBAR. The team has three lives," +
                        "which are lost if a player crashes into an alien spaceship or if an alien spaceship gets past them." +
                        "Stay focused and take down the enemies!</html>";
                wavPath = "src/main/java/resources/Level3Instructions.wav";
            }
            case 4 -> {
                this.bodyLabel.setFont(new Font("SansSerif", Font.PLAIN, 21));
                path = "src/main/java/resources/level2instructions.png";
                strBody = "<html>In this level, just like in Level 2, only Player 1 can move the astronaut" +
                        "left and right inside the spaceship using the Arrow Keys to find the hidden malfunction " +
                        "in the system. Once the players identify the issue, they must work together to complete three" +
                        "progressively difficult Circuit Breaker challenges-puzzle-based tasks where they need to restore" +
                        "power by correctly reconnecting disrupted circuits in the spaceship's electrical system." +
                        "Only after successfully overcoming all three challenges will they be able to advance to the next level." +
                        "<br>Stay sharp and work as a team! ";
                wavPath = "src/main/java/resources/Level4Instructions.wav";
            }
            case 5 -> {
                this.bodyLabel.setFont(new Font("SansSerif", Font.PLAIN, 21));
                path = "src/main/java/resources/level5instructions.png";
                strBody = "<html>In this level, the two astronauts have landed on the planet they aim to conquer." +
                        "They can move left and right-each player using their respective keys-and shoot lasers " +
                        "(just like in Level 3) to eliminate the aliens. Additionally, an alien spaceship hovers " +
                        "in the sky, randomly firing lasers at the players." +
                        "To complete the level, the players must eliminate 30 aliens without getting hit even once." +
                        "A failure occurs if an astronaut is hit by an alien or by a laser from the alien spaceship." +
                        "Each player has exactly 15 laser shots, meaning they must work together strategically to succeed." +
                        "<br>Coordination and precision are key to victory! ";
                wavPath = "src/main/java/resources/Level5Instructions.wav";
            }

        }
        this.bodyLabel.setText(strBody);
        spokenSound.playSound(wavPath);

        spokenButton.addActionListener(e -> {
            spokenSound.startPlay();
            pauseButton.setVisible(true);
            if (isPause){
                isPause = false;
                pauseButton.setText("Pause");
            }
        });

        pauseButton.addActionListener(e -> {
            if (!isPause){
                spokenSound.pause();
                isPause = true;
                pauseButton.setText("Play");
            } else {
                spokenSound.play();
                isPause = false;
                pauseButton.setText("Pause");
            }
        });
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


    public Sound getSpokenSound() {
        return spokenSound;
    }
}
