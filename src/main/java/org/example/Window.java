package org.example;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private OpeningPanel openingPanel;
    private LevelsPanel levelsPanel;
    private final int width = 1100;
    private final int height = 750;

    public Window () {
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);


        levelsPanel = new LevelsPanel(width, height);
        this.add(levelsPanel);
        openingPanel = new OpeningPanel(width, height);
        this.add(openingPanel);

        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 20));
        UIManager.put("Button.background", Color.darkGray);
        UIManager.put("Button.foreground", Color.white);
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 18));

        this.openingPanel.getEnterButton().addActionListener(e -> {
            if (!this.openingPanel.hasEmptyField()) {
                this.openingPanel.setVisible(false);
//                this.openingPanel.restartPanel();
                this.levelsPanel.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "One or more of your fields is empty, \nplease fill in these fields", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        this.levelsPanel.getReturnButton().addActionListener(e -> {
            this.levelsPanel.setVisible(false);
            this.openingPanel.setVisible(true);
        });

    }
    public void showWindow () {
        this.setVisible(true);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
