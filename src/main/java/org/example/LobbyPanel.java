package org.example;

import org.w3c.dom.css.RGBColor;

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


    private int levelButtonsX = 50; // נסה לשנות את הערך כדי להתאים למיקום בתמונה
    private int levelButtonsY = 580; // מיקום הקרוב יותר לתחתית התמונה
    private int space = 40; // המרווח בין הכפתורים




    public LobbyPanel(int width, int height) {
        this.setBackground(Color.orange);
        this.lobbyBackground = new ImageIcon("src/main/java/resources/LevelsBackground-V.png");
        this.setSize(width, height);
        this.setLayout(null);
        this.setVisible(false);

        this.title = new JLabel("Levels:");
        this.title.setForeground(new Color(31, 191, 230));
        this.title.setFont(new Font("Arial", Font.BOLD , 50));
        this.title.setBounds((width - 180) / 2, 110, 180, 70);

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




        this.levelButton1 = new LevelButton(80, 520);
        this.levelButton1.setEnabled(true);
        this.add(levelButton1);

        this.levelButton2 = new LevelButton(275, 520);
        this.levelButton2.setEnabled(true);
        this.add(levelButton2);

        this.levelButton3 = new LevelButton(480, 520);
        this.levelButton3.setEnabled(true);//***
        this.add(levelButton3);

        this.levelButton4 = new LevelButton(680, 520);
        this.levelButton4.setEnabled(true);
        this.add(levelButton4);

        this.levelButton5 = new LevelButton(880, 520);
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
}
