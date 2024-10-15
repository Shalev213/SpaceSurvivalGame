package org.example;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class ButtonsPanel extends JPanel {
    private final int panelWidth;
    private final int panelHeight;
    private Sound sceneSound;
    private JButton nextLevelButton;
    private JButton lobbyButton;
    private JButton checkButton;
    private ImageIcon riddleBackground;
    private JButton exitButton;
    private JButton hintsButton;
    private int exitButtonWidth = 80;
    private ColorButton colorButton1;
    private ColorButton colorButton2;
    private ColorButton colorButton3;
    private ColorButton colorButton4;
    private ColorButton colorButton5;
    private ColorButton colorButton6;
    private int spaceBetweenButtons = 7;
    private JLabel isSuccessLabel;
    private Sound wrongSound;
    private Sound passedLevel;
    private Sound missionComplete;


    public ButtonsPanel(){
        this.riddleBackground = new ImageIcon("src/main/java/resources/RiddleBackground.png");
        this.panelWidth = this.riddleBackground.getIconWidth();
        this.panelHeight = this.riddleBackground.getIconHeight();

        this.sceneSound = new Sound();
        this.sceneSound.playSound("src/main/java/resources/spaceship-alarm.wav");

        this.wrongSound = new Sound();
        this.wrongSound.playSound("src/main/java/resources/wrong_answer.wav");

        this.passedLevel = new Sound();
        this.passedLevel.playSound("src/main/java/resources/passed_level.wav");

        this.missionComplete = new Sound();
        this.missionComplete.playSound("src/main/java/resources/mission_completed.wav");


        this.setSize(panelWidth, panelHeight);
        this.setLayout(null);
        this.setVisible(false);

        this.isSuccessLabel = new JLabel("<html><div style='text-align: center;'>Find the<br/>right order</div><html>");
        this.isSuccessLabel.setFont(new Font("Arial", Font.BOLD , 37));
        this.isSuccessLabel.setBounds((panelWidth - 200) / 2, 170, 190, 110);
        this.isSuccessLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.isSuccessLabel.setForeground(new Color(176,224,219));
        this.add(isSuccessLabel);

        this.colorButton1 = new ColorButton(88, 0);
        this.colorButton2 = new ColorButton(this.colorButton1.getY() + this.colorButton1.getHeight() + this.spaceBetweenButtons, 1);
        this.colorButton3 = new ColorButton(this.colorButton2.getY() + this.colorButton2.getHeight() + this.spaceBetweenButtons, 2);
        this.colorButton4 = new ColorButton(this.colorButton3.getY() + this.colorButton3.getHeight() + this.spaceBetweenButtons, 3);
        this.colorButton5 = new ColorButton(this.colorButton4.getY() + this.colorButton4.getHeight() + this.spaceBetweenButtons, 4);
        this.colorButton6 = new ColorButton(this.colorButton5.getY() + this.colorButton5.getHeight() + this.spaceBetweenButtons, 5);

        this.add(colorButton1);
        this.add(colorButton2);
        this.add(colorButton3);
        this.add(colorButton4);
        this.add(colorButton5);
        this.add(colorButton6);

        this.hintsButton = new JButton("hints");
        this.hintsButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.hintsButton.setBounds((this.panelWidth - this.exitButtonWidth) / 11, 135, this.exitButtonWidth,40);
        this.hintsButton.setFocusPainted(false);
        this.hintsButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.hintsButton.setBackground(new Color(176,224,219));
        this.hintsButton.setForeground(new Color(0,0,0));

        this.add(hintsButton);


        this.checkButton = new JButton("check");
        this.checkButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.checkButton.setBounds(this.colorButton1.getX() - (this.exitButtonWidth - this.colorButton1.getWidth()), this.colorButton6.getY() + this.colorButton6.getHeight() + 2 * this.spaceBetweenButtons, this.exitButtonWidth,40);
        this.checkButton.setFocusPainted(false);
        this.checkButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.checkButton.setBackground(new Color(176,224,219));
        this.checkButton.setForeground(new Color(0,0,0));

        this.add(checkButton);


        this.exitButton = new JButton("exit");
        this.exitButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.exitButton.setBounds((this.panelWidth - this.exitButtonWidth) / 11, 225, this.exitButtonWidth,40);
        this.exitButton.setFocusPainted(false);
        this.exitButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.exitButton.setBackground(new Color(176,224,219));
        this.exitButton.setForeground(new Color(0,0,0));

        this.exitButton.addActionListener(e -> {
            this.setVisible(false);
        });

        this.add(exitButton);

        this.nextLevelButton = new JButton("Next level");
        this.nextLevelButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.nextLevelButton.setBounds(this.panelWidth / 2, this.panelHeight - 2 * 43, (int) (1.5 * this.exitButtonWidth),35);
        this.nextLevelButton.setFocusPainted(false);
        this.nextLevelButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.nextLevelButton.setBackground(new Color(244, 213, 2));
        this.nextLevelButton.setForeground(new Color(0,0,0));


        this.lobbyButton = new JButton("Lobby");
        this.lobbyButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.lobbyButton.setBounds((int) ((this.panelWidth / 2) - 1.4 * this.exitButtonWidth - 3 * this.spaceBetweenButtons), this.panelHeight - 2 * 43, (int) (1.4 * this.exitButtonWidth),35);
        this.lobbyButton.setFocusPainted(false);
        this.lobbyButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.lobbyButton.setBackground(new Color(244, 213, 2));
        this.lobbyButton.setForeground(new Color(0,0,0));


    }
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (riddleBackground != null){
            this.riddleBackground.paintIcon(null, graphics, 0, 0);
        }

    }

    public JButton getHintsButton() {
        return hintsButton;
    }

    public boolean isSuccess() {
        System.out.println(STR."1) \{colorButton1.getColor()}2) \{colorButton2.getColor()}3) \{colorButton3.getColor()}4) \{colorButton4.getColor()}5) \{colorButton5.getColor()}6) \{colorButton6.getColor()}");
        return colorButton1.getColor().equals(Color.MAGENTA) && colorButton2.getColor().equals(Color.YELLOW) && colorButton3.getColor().equals(Color.WHITE) && colorButton4.getColor().equals(Color.RED) && colorButton5.getColor().equals(Color.GREEN) && colorButton6.getColor().equals(Color.BLUE);

    }

    public void success() {
        this.passedLevel.startPlay();
        this.missionComplete.startPlay();
        this.isSuccessLabel.setText("SUCCESS");
        this.isSuccessLabel.setForeground(Color.GREEN);
        this.add(nextLevelButton);
        this.add(lobbyButton);
    }

    public void failure() {
        this.wrongSound.startPlay();
        this.isSuccessLabel.setText("FAILURE");
        this.isSuccessLabel.setForeground(Color.RED);
        this.remove(nextLevelButton);
        this.remove(lobbyButton);
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getCheckButton() {
        return checkButton;
    }

    public JButton getNextLevelButton() {
        return nextLevelButton;
    }

    public JButton getLobbyButton() {
        return lobbyButton;
    }
}
