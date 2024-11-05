package org.example;

import db.JDBC;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class LobbyPanel extends JPanel {
    private final JButton exitButton;
    private final ImageIcon lobbyBackground;
    private JLabel title;
    private LevelButton levelButton1;
    private LevelButton levelButton2;
    private LevelButton levelButton3;
    private LevelButton levelButton4;
    private LevelButton levelButton5;
//    private int levelButtonsX = 60;
//    private int levelButtonsY = 200;
//    private int space = 30;
    private int titleWidth = 430; // נסה לשנות את הערך כדי להתאים למיקום בתמונה
    private int levelButtonsX = 67; // נסה לשנות את הערך כדי להתאים למיקום בתמונה
    private int levelButtonsY = 520; // מיקום הקרוב יותר לתחתית התמונה
    private int space = 43; // המרווח בין הכפתורים




    public LobbyPanel(int width, int height, String teamName) {
        this.teamName = teamName;

        this.lobbyBackground = new ImageIcon("src/main/java/resources/LevelsBackground-V.png");
        this.setSize(width, height);
        this.setLayout(null);
        this.setVisible(false);

        this.title = new JLabel("<html>CHOOSE<br>YOUR LEVEL:</html>");
        this.title.setForeground(new Color(31, 191, 230));
        this.title.setFont(new Font("Arial", Font.BOLD , 60));
        this.title.setBounds((width - this.titleWidth) / 10, 100, this.titleWidth, 150);

        this.add(title);

        this.exitButton = new JButton("EXIT");
        this.exitButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.exitButton.setBounds((width / 2) - 45,655,90,55);
        this.exitButton.setForeground(new Color(31, 191, 230));
        this.exitButton.setFocusPainted(false);
        this.exitButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.exitButton.setContentAreaFilled(false);
        this.exitButton.setBorder(BorderFactory.createLineBorder(new Color(31, 191, 230), 10));  // גבול בעובי 2 פיקסלים

        this.add(exitButton);



        this.levelButton1 = new LevelButton(this.levelButtonsX, this.levelButtonsY);
        this.levelButton1.setEnabled(true);
//        checkEnable(this.levelButton1);
        this.levelButton1.checkEnable();
        this.add(levelButton1);

        this.levelButton2 = new LevelButton(this.levelButtonsX + this.levelButton1.getWidth() + this.space, this.levelButtonsY);
        this.levelButton2.setEnabled(false);
//        checkEnable(this.levelButton1);
        this.levelButton2.checkEnable();

        this.add(levelButton2);

        this.levelButton3 = new LevelButton(this.levelButton2.getX() + this.levelButton2.getWidth() + this.space, this.levelButtonsY);
        this.levelButton3.setEnabled(false);//***

        this.levelButton3.checkEnable();
        this.add(levelButton3);

        this.levelButton4 = new LevelButton(this.levelButton3.getX() + this.levelButton3.getWidth() + this.space, this.levelButtonsY);
        this.levelButton4.setEnabled(false);
        this.levelButton4.checkEnable();
        this.add(levelButton4);

        this.levelButton5 = new LevelButton(this.levelButton4.getX() + this.levelButton4.getWidth() + this.space, this.levelButtonsY);
        this.levelButton5.setEnabled(false);
        this.levelButton5.checkEnable();
        this.add(levelButton5);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (lobbyBackground != null){
            this.lobbyBackground.paintIcon(null, graphics, 0, 0);
        }
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public LevelButton getLevelButton1() {
        return levelButton1;
    }

    public LevelButton getLevelButton2() {
        return levelButton2;
    }

    public LevelButton getLevelButton3() {
        return levelButton3;
    }

    public LevelButton getLevelButton4() {
        return levelButton4;
    }

    public LevelButton getLevelButton5() {
        return levelButton5;
    }

    public void updateLevel() {
        int currentLevel = JDBC.showUpdate(teamName);
        for (int i = 0; i < levelButtons.size(); i++) {
            if (i < currentLevel){
                levelButtons.get(i).setEnabled(true);
                levelButtons.get(i).checkEnable();
            }

        }
    }


}
