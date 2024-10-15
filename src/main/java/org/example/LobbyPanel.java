package org.example;

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
    private int levelButtonsX = 60;
    private int levelButtonsY = 200;
    private int space = 30;


    public LobbyPanel(int width, int height) {
        this.setBackground(Color.orange);
        this.lobbyBackground = new ImageIcon("");
        this.setSize(width, height);
        this.setLayout(null);
        this.setVisible(false);

        this.title = new JLabel("Levels:");
        this.title.setFont(new Font("Arial", Font.BOLD , 50));
        this.title.setBounds((width - 180) / 2, 110, 180, 70);

        this.add(title);

        this.exitButton = new JButton("exit");
        this.exitButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.exitButton.setBounds((width - 140) / 20,620,90,50);
        this.exitButton.setFocusPainted(false);
        this.exitButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.add(exitButton);

        this.levelButton1 = new LevelButton(levelButtonsX, levelButtonsY, "1");
        this.levelButton1.setEnabled(true);
        this.add(levelButton1);
        this.levelButton2 = new LevelButton(levelButtonsX + space + levelButton1.getWidth(), levelButtonsY, "2");
        this.levelButton2.setEnabled(true);//*** צריך להיות false
        this.add(levelButton2);
        this.levelButton3 = new LevelButton(levelButtonsX + 2 * (space + levelButton1.getWidth()), levelButtonsY, "3");
        this.levelButton3.setEnabled(true);//***
        this.add(levelButton3);
        this.levelButton4 = new LevelButton(levelButtonsX + 3 * (space + levelButton1.getWidth()), levelButtonsY, "4");
        this.levelButton4.setEnabled(true);//***
        this.add(levelButton4);
        this.levelButton5 = new LevelButton(levelButtonsX + 4 * (space + levelButton1.getWidth()), levelButtonsY, "5");
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
