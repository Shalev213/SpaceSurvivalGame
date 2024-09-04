package org.example;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class LevelsPanel extends JPanel {
    private final JButton returnButton;
    private JLabel title;
    private LevelButton levelButton1;
    private LevelButton levelButton2;
    private LevelButton levelButton3;
    private LevelButton levelButton4;
    private LevelButton levelButton5;
    private int levelButtonsX = 70;
    private int levelButtonsY = 200;
    private int space = 30;

    public LevelsPanel(int width, int height) {
        this.setBackground(Color.orange);
        this.setSize(width, height);
        this.setLayout(null);
        this.setVisible(false);

        this.title = new JLabel("Levels:");
        this.title.setFont(new Font("Arial", Font.BOLD , 50));
        this.title.setBounds((width - 180) / 2, 110, 180, 70);

        this.add(title);

        this.returnButton = new JButton("return");
        this.returnButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.returnButton.setBounds((width - 140) / 2,600,140,70);
        this.returnButton.setFocusPainted(false);
        this.returnButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.add(returnButton);

        this.levelButton1 = new LevelButton(levelButtonsX, levelButtonsY, "1");
        this.levelButton1.setEnabled(true);
        this.add(levelButton1);
        this.levelButton2 = new LevelButton(levelButtonsX + space + levelButton1.getWidth(), levelButtonsY, "2");
        this.add(levelButton2);
        this.levelButton3 = new LevelButton(levelButtonsX + 2 * (space + levelButton1.getWidth()), levelButtonsY, "3");
        this.add(levelButton3);
        this.levelButton4 = new LevelButton(levelButtonsX + 3 * (space + levelButton1.getWidth()), levelButtonsY, "4");
        this.add(levelButton4);
        this.levelButton5 = new LevelButton(levelButtonsX + 4 * (space + levelButton1.getWidth()), levelButtonsY, "5");
        this.add(levelButton5);
    }

    public JButton getReturnButton() {
        return returnButton;
    }
}
