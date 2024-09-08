package org.example;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private SignInPanel signInPanel;
    private SignUpPanel signUpPanel;
    private LevelsPanel levelsPanel;
    private final int width = 1100;
    private final int height = 750;

    public Window () {
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);


        signInPanel = new SignInPanel(width, height);
        this.add(signInPanel);
        signUpPanel = new SignUpPanel(width, height);
        this.add(signUpPanel);
        signUpPanel.setVisible(false);
        levelsPanel = new LevelsPanel(width, height);
        this.add(levelsPanel);
        levelsPanel.setVisible(false);



        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 20));
        UIManager.put("Button.background", Color.darkGray);
        UIManager.put("Button.foreground", Color.white);
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 18));

        this.signInPanel.getEnterButton().addActionListener(e -> {
            if (!this.signInPanel.hasEmptyField()) {
                this.signInPanel.setVisible(false);
                this.signInPanel.restartPanel();
                this.levelsPanel.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "One or more of your fields is empty, \nplease fill in these fields", "Error", JOptionPane.ERROR_MESSAGE);
            }


        });
        this.levelsPanel.getReturnButton().addActionListener(e -> {
            this.levelsPanel.setVisible(false);
            this.signInPanel.setVisible(true);
        });
        this.signInPanel.getSignUpButton().addActionListener(e -> {
            this.signInPanel.setVisible(false);
            this.signInPanel.restartPanel();
            this.signUpPanel.setVisible(true);
        });
        this.signUpPanel.getSignUpButton().addActionListener(e -> {
            if (this.signUpPanel.isVerifiedPassword()) {
                this.signUpPanel.setVisible(false);
//                this.signUpPanel.restartPanel();
                this.signInPanel.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Your passwords are not similar or empty, \nplease correct it", "Error", JOptionPane.ERROR_MESSAGE);
            }
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
