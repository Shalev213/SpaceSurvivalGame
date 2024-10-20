    package org.example;

    import db.JDBC;

    import javax.swing.*;
    import java.awt.*;

    public class Window extends JFrame {
        private final int windowWidth = 1100;
        private final int windowHeight = 750;
        private SignInPanel signInPanel;
        private SignUpPanel signUpPanel;
        private LobbyPanel lobbyPanel;
        private LevelOne levelOne;
        private LevelTwo levelTwo;
        private LevelThree levelThree;
        private LevelFour levelFour;
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
            lobbyPanel = new LobbyPanel(windowWidth, windowHeight);
            this.add(lobbyPanel);
            lobbyPanel.setVisible(false);

//            levelTwo = new LevelTwo(windowWidth, windowHeight);


//            levelFour = new LevelFour(windowWidth, windowHeight);


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
//                    if (JDBC.validateLogin(teamName, teamPassword)) {
//                        System.out.println("login success");
                        this.signInPanel.setVisible(false);
                        this.signInPanel.restartPanel();
                        this.lobbyPanel.setVisible(true);

////                    }
//                    else {
//                        JOptionPane.showMessageDialog(null, "Your team name or password are not exist, \nplease correct them or create a new team", "Error", JOptionPane.ERROR_MESSAGE);
//                    }
                }
            });
            this.lobbyPanel.getExitButton().addActionListener(e -> {
                this.lobbyPanel.setVisible(false);
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

            this.lobbyPanel.getLevelButton1().addActionListener(e -> {
                this.lobbyPanel.setVisible(false);
                String teamName = signInPanel.getTeamName();
                this.levelOne = new LevelOne(windowWidth, windowHeight, teamName);
                this.add(levelOne);

                this.levelOne.addOptionSelectionListener(selectedOption -> {
                    if (selectedOption == 0 || selectedOption == JOptionPane.CLOSED_OPTION) {
                        this.levelOne.getSceneSound().stopPlay();
                        this.remove(levelOne);
                        lobbyPanel.setVisible(true);
                        if (levelOne.isSuccess()) {
                            this.lobbyPanel.getLevelButton2().setEnabled(true);
                            this.lobbyPanel.getLevelButton2().checkEnable();//************
                        }
                    } else if (selectedOption == 1) {
                        if (levelOne.isSuccess()) {
                            levelOne.getSceneSound().stopPlay();
                            this.remove(levelOne);
                            this.lobbyPanel.getLevelButton2().setEnabled(true);
                            this.lobbyPanel.getLevelButton2().checkEnable();//**********
                            this.lobbyPanel.getLevelButton2().doClick();
                        } else if (levelOne.isFailed()) {
                            this.levelOne.getSceneSound().stopPlay();
                            this.remove(levelOne);
                            this.lobbyPanel.getLevelButton1().doClick();
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


            this.lobbyPanel.getLevelButton2().addActionListener(e -> {
                this.lobbyPanel.setVisible(false);
                levelTwo = new LevelTwo(windowWidth, windowHeight);
                this.add(levelTwo);
//                levelTwo.setGameCondition(true);
                levelTwo.setVisible(true);
                levelTwo.setFocusable(true);
                levelTwo.requestFocus();
                levelTwo.requestFocusInWindow();
                this.levelTwo.getNextLevelButton().addActionListener(_ ->{
                    this.levelTwo.getSceneSound().stopPlay();
                    this.remove(this.levelTwo);
                    this.lobbyPanel.getLevelButton3().setEnabled(true);
                    this.lobbyPanel.getLevelButton3().checkEnable();//**********
                    this.lobbyPanel.getLevelButton3().doClick();
                });
                this.levelTwo.getLobbyButton().addActionListener(_ ->{
                    this.levelTwo.getSceneSound().stopPlay();
                    this.remove(this.levelTwo);
                    this.lobbyPanel.setVisible(true);
//                if (levelOne.isSuccess()){
                    this.lobbyPanel.getLevelButton3().setEnabled(true);
                    this.lobbyPanel.getLevelButton3().checkEnable();
                });
            });


            this.lobbyPanel.getLevelButton3().addActionListener(e -> {
                this.lobbyPanel.setVisible(false);
                String teamName = signInPanel.getTeamName();
                this.levelThree = new LevelThree(windowWidth, windowHeight, teamName);
                this.add(levelThree);

                levelThree.addOptionSelectionListener(selectedOption -> {
                    if (selectedOption == 0 || selectedOption == JOptionPane.CLOSED_OPTION) {
                        levelThree.getSceneSound().stopPlay();
                        this.remove(levelThree);
                        lobbyPanel.setVisible(true);
                        if (levelThree.isSuccess()){
                            this.lobbyPanel.getLevelButton4().setEnabled(true);
                            this.lobbyPanel.getLevelButton4().checkEnable();//**********
                        }
                    } else if (selectedOption == 1) {
                        if (levelThree.isSuccess()) {
                            levelThree.getSceneSound().stopPlay();
                            this.remove(levelThree);
                            this.lobbyPanel.getLevelButton4().setEnabled(true);
                            this.lobbyPanel.getLevelButton4().checkEnable();//**********
                            this.lobbyPanel.getLevelButton4().doClick();
                        } else if (levelThree.isFailed()) {
                            this.levelThree.getSceneSound().stopPlay();
                            this.remove(levelThree);
                            this.lobbyPanel.getLevelButton3().doClick();
                        }
                    }else {
                        System.out.println("No option selected or window closed");
                    }
                });

                levelThree.setVisible(true);
                levelThree.setFocusable(true);
                levelThree.requestFocus();
                levelThree.requestFocusInWindow();
            });



            this.lobbyPanel.getLevelButton4().addActionListener(e -> {
                this.lobbyPanel.setVisible(false);


                this.levelFour = new LevelFour(windowWidth, windowHeight);
                this.add(levelFour);
                levelFour.setVisible(true);
//                levelFour.setFocusable(true);
//                levelFour.requestFocus();
//                levelFour.requestFocusInWindow();

//                this.lobbyPanel.setVisible(false);
//                String teamName = signInPanel.getTeamName();
//                this.levelThree = new LevelThree(windowWidth, windowHeight, teamName);
//                this.add(levelThree);



                levelFour.addOptionSelectionListener(selectedOption -> {
                    if (selectedOption == 0 || selectedOption == JOptionPane.CLOSED_OPTION) {
//                        levelFour.getSceneSound().stopPlay();
                        this.remove(levelFour);
                        lobbyPanel.setVisible(true);
                        if (levelFour.isSuccess()){
                            this.lobbyPanel.getLevelButton5().setEnabled(true);
                            this.lobbyPanel.getLevelButton5().checkEnable();//**********
                        }
                    } else if (selectedOption == 1) {
                        if (levelFour.isSuccess()) {

                            if (levelFour.getLevelCounter() <= 3){
                                System.out.println(levelFour.getLevelCounter());
                                this.remove(levelFour);
                                this.lobbyPanel.getLevelButton4().doClick();
                                this.levelFour.getCircuitButton().doClick();
                            } else {
//                            levelFour.getSceneSound().stopPlay();
                                this.remove(levelFour);
                                this.lobbyPanel.getLevelButton5().setEnabled(true);
                                this.lobbyPanel.getLevelButton5().checkEnable();//**********
                                this.lobbyPanel.getLevelButton5().doClick();
                                this.levelFour.setCounterOfLevel(1);
                            }
                        } else if (levelFour.isFailed()) {
//                            this.levelFour.getSceneSound().stopPlay();
                            this.remove(levelFour);
                            this.lobbyPanel.getLevelButton4().doClick();
                            this.levelFour.getCircuitButton().doClick();
                        }
                    }else {
                        System.out.println("No option selected or window closed");
                    }
                });

                levelFour.setVisible(true);
                levelFour.setFocusable(true);
                levelFour.requestFocus();
                levelFour.requestFocusInWindow();
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
