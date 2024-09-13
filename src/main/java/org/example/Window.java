package org.example;

import db.JDBC;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private SignInPanel signInPanel;
    private SignUpPanel signUpPanel;
    private LevelsPanel levelsPanel;
    private LevelOne levelOne;
    private LevelTwo levelTwo;
    private LevelThree levelThree;
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

        levelOne = new LevelOne(width, height);
//        this.add(levelOne);
//        levelOne.setVisible(false);
        levelTwo = new LevelTwo(width, height);
        levelThree = new LevelThree(width, height);




        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 20));
        UIManager.put("Button.background", Color.darkGray);
        UIManager.put("Button.foreground", Color.white);
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 18));

        this.signInPanel.getLoginButton().addActionListener(e -> {
            String teamName = signInPanel.getTeamName();
            String teamPassword = signInPanel.getPassword();  // הוספתי לבנתיים במידה ויצטרך לפונקציית-validate         **** תוקן *****
            if (this.signInPanel.hasEmptyField()) {
                JOptionPane.showMessageDialog(null, "One or more of your fields are empty, \nplease fill in these fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (JDBC.validateLogin(teamName, teamPassword)) {    //שינינו ל-validate?    **** תוקן *****
                    System.out.println("login success");
                    this.signInPanel.setVisible(false);
                    this.signInPanel.restartPanel();
                    this.levelsPanel.setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null, "Your team name or password are not exist, \nplease correct them or create a new team", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        this.levelsPanel.getReturnButton().addActionListener(e -> {
            this.levelsPanel.setVisible(false);
            this.signInPanel.setVisible(true);
        });
        this.signUpPanel.getReturnButton().addActionListener(e -> {
            this.signUpPanel.setVisible(false);
            this.signInPanel.setVisible(true);
        });
        this.signInPanel.getSignUpButton().addActionListener(e -> {
            this.signInPanel.setVisible(false);
            this.signInPanel.restartPanel();
            this.signUpPanel.setVisible(true);
        });
        this.signUpPanel.getRegisterButton().addActionListener(e -> {
            String teamName = signUpPanel.getTeamName();
            String teamPassword = signUpPanel.getTeamPassword();

            if (this.signUpPanel.isVerifiedPassword()) {
                if (!this.signUpPanel.hasEmptyField()) {
                    if (JDBC.isNameExist(teamName)) {
                        JDBC.register(teamName, teamPassword);// add a condition to check if the name exist - don't return to the SignIn Panel.
                        this.signUpPanel.setVisible(false);
//                this.signUpPanel.restartPanel();
                        this.signInPanel.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Your team name is taken, \nplease change it", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "One or more of your fields is empty, \nplease fill in these fields", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Your passwords are not similar or empty, \nplease correct it", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.levelsPanel.getLevelButton1().addActionListener(e -> {
            this.levelsPanel.setVisible(false);
            this.add(levelOne);
            levelOne.setVisible(true);
        });

        this.levelsPanel.getLevelButton2().addActionListener(e -> {
            this.levelsPanel.setVisible(false);
            this.add(levelTwo);
            levelTwo.setVisible(true);

        }); this.levelsPanel.getLevelButton3().addActionListener(e -> {
            this.levelsPanel.setVisible(false);
            this.add(levelThree);
            levelThree.setVisible(true);
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
