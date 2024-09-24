    package org.example;

    import db.JDBC;

    import javax.swing.*;
    import java.awt.*;

    public class Window extends JFrame {
        private final int windowWidth = 1100;
        private final int windowHeight = 750;
        private SignInPanel signInPanel;
        private SignUpPanel signUpPanel;
        private LevelsPanel levelsPanel;
        private LevelOne levelOne;
        private LevelTwo levelTwo;
        private LevelThree levelThree;
//        private Sounds sceneSound;



        public Window () {
            this.setSize(windowWidth, windowHeight);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setResizable(false);
            this.setLocationRelativeTo(null);

//            this.sceneSound = new Sounds();

            signInPanel = new SignInPanel(windowWidth, windowHeight);
            this.add(signInPanel);
            signUpPanel = new SignUpPanel(windowWidth, windowHeight);
            this.add(signUpPanel);
            signUpPanel.setVisible(false);
            levelsPanel = new LevelsPanel(windowWidth, windowHeight);
            this.add(levelsPanel);
            levelsPanel.setVisible(false);

            levelTwo = new LevelTwo(windowWidth, windowHeight);
//            levelThree = new LevelThree(windowWidth, windowHeight);

            UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 20));
            UIManager.put("Button.background", Color.darkGray);
            UIManager.put("Button.foreground", Color.white);
            UIManager.put("Button.font", new Font("Arial", Font.BOLD, 18));

            this.signInPanel.getLoginButton().addActionListener(e -> {
                String teamName = signInPanel.getTeamName();
                String teamPassword = signInPanel.getPassword();
                if (this.signInPanel.hasEmptyField()) {
                    JOptionPane.showMessageDialog(null, "One or more of your fields are empty, \nplease fill in these fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (JDBC.validateLogin(teamName, teamPassword)) {
                        System.out.println("login success");
                        this.signInPanel.setVisible(false);
                        this.signInPanel.restartPanel();
                        this.levelsPanel.setVisible(true);
                    } else {
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
                            JDBC.register(teamName, teamPassword);
                            this.signUpPanel.setVisible(false);
                            this.signInPanel.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Your team name is taken, \nplease change it", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "One or more of your fields is empty, \nplease fill in these fields", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Your passwords are not similar, \nplease correct it", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            this.levelsPanel.getLevelButton1().addActionListener(e -> {
                this.levelsPanel.setVisible(false);
                String teamName = signInPanel.getTeamName();
                levelOne = new LevelOne(windowWidth, windowHeight, teamName);
                this.add(levelOne);

                levelOne.addOptionSelectionListener(selectedOption -> {
                    if (selectedOption == 0) {
                        levelOne.getSceneSound().stopPlay();
                        this.remove(levelOne);
                        levelsPanel.setVisible(true);
                        if (levelOne.isSuccess()){
                            this.levelsPanel.getLevelButton2().setEnabled(true);
                        }
                        // פעולה ל-"Lobby"
                    } else if (selectedOption == 1) {
                        if (levelOne.isSuccess()) {
                            levelOne.getSceneSound().stopPlay();
                            this.remove(levelOne);
                            this.levelsPanel.getLevelButton2().setEnabled(true);
                            this.levelsPanel.getLevelButton2().doClick();
                        } else if (levelOne.isFailed()) {
                            this.levelOne.getSceneSound().stopPlay();
                            this.remove(levelOne);
                            this.levelsPanel.getLevelButton1().doClick();
                        }
                    } else {
                        System.out.println("No option selected or window closed");
                    }
                });

                levelOne.setVisible(true);
                levelOne.setFocusable(true);
                levelOne.requestFocus();
                levelOne.requestFocusInWindow();
            });

            this.levelsPanel.getLevelButton2().addActionListener(e -> {
                this.levelsPanel.setVisible(false);
                this.add(levelTwo);
                levelTwo.setVisible(true);
                levelTwo.setFocusable(true);
                levelTwo.requestFocus();
                levelTwo.requestFocusInWindow();
            });
            this.levelsPanel.getLevelButton3().addActionListener(e -> {
                this.levelsPanel.setVisible(false);
                this.add(levelThree);
                levelThree.setVisible(true);
            });


        }

        public void showWindow () {
            this.setVisible(true);
        }

        public int getWidth() {
            return windowWidth;
        }

        public int getHeight() {
            return windowHeight;
        }





    }
