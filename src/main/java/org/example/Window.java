package org.example;

import db.JDBC;
import opencv.OpenCVProcessor;

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
    private LevelFive levelFive;
    private Sound lobbyBackground;
    private LevelInstructions levelInstructions;

    private String teamNameExist;

//        private GameOverScreen gameOverScreen;
//        private Sounds sceneSound;


    public Window() {
        this.setSize(windowWidth, windowHeight);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

//            this.sceneSound = new Sounds();

        signInPanel = new SignInPanel(windowWidth, windowHeight);
        this.add(signInPanel);

        this.lobbyBackground = new Sound();
        this.lobbyBackground.playSound("src/main/java/resources/background_sound.wav");
        this.lobbyBackground.startBackgroundPlay();
        this.lobbyBackground.loopPlay();
//            this.lobbyBackground.restartSound();

        signUpPanel = new SignUpPanel(windowWidth, windowHeight);
        this.add(signUpPanel);
        signUpPanel.setVisible(false);

        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 20));
        UIManager.put("Button.background", Color.darkGray);
        UIManager.put("Button.foreground", Color.white);
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 18));

        this.signInPanel.getLoginButton().addActionListener(e -> {


            teamNameExist = signInPanel.getTeamName();

            this.lobbyPanel = new LobbyPanel(windowWidth, windowHeight);
            this.add(lobbyPanel);


            addLobbyPanelListeners();
            lobbyPanel.setTeamName(teamNameExist);
            String teamPassword = signInPanel.getPassword();
            if (this.signInPanel.hasEmptyField()) {
                JOptionPane.showMessageDialog(null, "One or more of your fields are empty, \nplease fill in these fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (JDBC.validateLogin(teamNameExist, teamPassword)) {
                    this.signInPanel.setVisible(false);
                    this.signInPanel.restartPanel();

                    this.lobbyPanel.updateLevel();
                    this.lobbyPanel.setVisible(true);



                    this.lobbyPanel.getInstructionsButton().addActionListener(e1 -> {
                        int currentLevel = JDBC.showUpdate(teamNameExist);
                        this.levelInstructions = new LevelInstructions(currentLevel, this.windowWidth, this.windowHeight);
//                        this.levelInstructions.setVisible(false);

                        this.add(levelInstructions);

                        this.lobbyPanel.setVisible(false);
                        this.levelInstructions.setVisible(true);
                        this.lobbyBackground.stopPlay();

                        this.levelInstructions.getLobbyButton().addActionListener(e2 -> {
//                            this.levelInstructions.setVisible(false);
//                            this.levelInstructions.getSpokenSound().stopPlay();
                            this.remove(levelInstructions);
                            this.lobbyPanel.setVisible(true);
                            this.lobbyBackground.restartSound();

                        });


                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Your team name or password are not exist, \nplease correct them or create a new team", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.signUpPanel.getReturnButton().addActionListener(e -> {
            this.signUpPanel.setVisible(false);
            this.signInPanel.setVisible(true);
        });
        this.signInPanel.getSignUpButton().addActionListener(e -> {
            this.signInPanel.setVisible(false);
            this.signInPanel.restartPanel();
            this.signUpPanel.setVisible(true);
            this.signUpPanel.resetPanel();
        });
        this.signUpPanel.getRegisterButton().addActionListener(e -> {
            String teamName = signUpPanel.getTeamName();
            String teamPassword = signUpPanel.getTeamPassword();

            if (this.signUpPanel.isVerifiedPassword()) {
                if (!this.signUpPanel.hasEmptyField()) {
                    if (!JDBC.isNameExist(teamName)) {
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

    }

    private void addLobbyPanelListeners() {
        addExitLobbyListener();
        addLevelOneListener();
        addLevelTwoListener();
        addLevelThreeListener();
        addLevelFourListener();
        addLevelFiveListener();
    }

    private void addLevelOneListener() {
        this.lobbyPanel.getLevelButton1().addActionListener(_ -> {
            this.lobbyBackground.stopPlay();
            this.lobbyPanel.setVisible(false);
//                teamNameExist = signInPanel.getTeamName();
            this.levelOne = new LevelOne(windowWidth, windowHeight, teamNameExist);
            this.add(levelOne);


            this.levelOne.addOptionSelectionListener(selectedOption -> {
                if (selectedOption == 0 || selectedOption == JOptionPane.CLOSED_OPTION) {
                    this.levelOne.getSceneSound().stopPlay();
                    this.remove(levelOne);
                    lobbyPanel.setVisible(true);
                    this.lobbyBackground.restartSound();
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
                }
            });

            levelOne.setVisible(true);
            levelOne.setFocusable(true);
            levelOne.requestFocus();
            levelOne.requestFocusInWindow();
        });
    }

    private void addLevelTwoListener() {
        this.lobbyPanel.getLevelButton2().addActionListener(e -> {
            this.lobbyBackground.stopPlay();
            this.lobbyPanel.setVisible(false);
//                teamNameExist = signInPanel.getTeamName();
            levelTwo = new LevelTwo(windowWidth, windowHeight, teamNameExist);
            this.add(levelTwo);
//                levelTwo.setGameCondition(true);
            levelTwo.setVisible(true);
            levelTwo.setFocusable(true);
            levelTwo.requestFocus();
            levelTwo.requestFocusInWindow();
            this.levelTwo.getNextLevelButton().addActionListener(_ -> {
                this.levelTwo.getSceneSound().stopPlay();
                this.remove(this.levelTwo);
                this.lobbyPanel.getLevelButton3().setEnabled(true);
                this.lobbyPanel.getLevelButton3().checkEnable();//**********
                this.lobbyPanel.getLevelButton3().doClick();
            });
            this.levelTwo.getLobbySuccessButton().addActionListener(_ -> {
                this.levelTwo.getSceneSound().stopPlay();
                this.remove(this.levelTwo);
                this.lobbyPanel.setVisible(true);
                this.lobbyBackground.restartSound();

//                if (levelOne.isSuccess()){
                this.lobbyPanel.getLevelButton3().setEnabled(true);
                this.lobbyPanel.getLevelButton3().checkEnable();
            });
            this.levelTwo.getLobbyButton().addActionListener(e1 -> {
                this.levelTwo.getSceneSound().stopPlay();
                this.levelTwo.setVisible(false);
                this.remove(levelTwo);
                this.lobbyPanel.setVisible(true);
                this.lobbyBackground.restartSound();
            });
        });
    }

    private void addLevelThreeListener() {
        this.lobbyPanel.getLevelButton3().addActionListener(_ -> {
            this.lobbyBackground.stopPlay();
            this.lobbyPanel.setVisible(false);

            this.levelThree = new LevelThree(windowWidth, windowHeight, teamNameExist);
            this.add(levelThree);

            levelThree.addOptionSelectionListener(selectedOption -> {
                if (selectedOption == 0 || selectedOption == JOptionPane.CLOSED_OPTION) {
                    levelThree.getSceneSound().stopPlay();
                    this.remove(levelThree);
                    lobbyPanel.setVisible(true);
                    OpenCVProcessor.release();
                    this.lobbyBackground.restartSound();
                    if (levelThree.isSuccess()) {
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
                }
            });

            levelThree.setVisible(true);
            levelThree.setFocusable(true);
            levelThree.requestFocus();
            levelThree.requestFocusInWindow();
        });
    }

    private void addLevelFourListener() {
        this.lobbyPanel.getLevelButton4().addActionListener(_ -> {
            this.lobbyBackground.stopPlay();
            this.lobbyPanel.setVisible(false);
            this.levelFour = new LevelFour(windowWidth, windowHeight, teamNameExist);
            this.add(levelFour);

            levelFour.addOptionSelectionListener(selectedOption -> {
                if (selectedOption == 0 || selectedOption == JOptionPane.CLOSED_OPTION) {
//                        this.levelFour.getSceneSound().stopPlay();
                    this.remove(levelFour);
                    lobbyPanel.setVisible(true);
//                        this.lobbyBackground.startBackgroundPlay();
                    this.lobbyBackground.loopPlay();
                    if (levelFour.isSuccess()) {
                        this.lobbyPanel.getLevelButton5().setEnabled(true);
                        this.lobbyPanel.getLevelButton5().checkEnable();//**********
                    }
                } else if (selectedOption == 1) {
                    if (levelFour.isSuccess()) {

                        if (levelFour.getLevelCounter() <= 3) {
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
                }
            });


            levelFour.setVisible(true);
            levelFour.setFocusable(true);
            levelFour.requestFocus();
            levelFour.requestFocusInWindow();

            this.levelFour.getLobbyButton().addActionListener(event -> {
                this.levelFour.setVisible(false);
                this.remove(levelFour);
                this.lobbyPanel.setVisible(true);
                this.lobbyBackground.restartSound();
            });
        });
    }

    private void addLevelFiveListener() {
        this.lobbyPanel.getLevelButton5().addActionListener(e -> {
            this.lobbyBackground.stopPlay();
            this.lobbyPanel.setVisible(false);

            this.levelFive = new LevelFive(windowWidth, windowHeight);
            levelFive.setVisible(true);
            this.add(levelFive);

//                this.levelFive.getFinalPanel().setVisible(false);
            this.levelFive.getFinalPanel().stop();
            this.add(this.levelFive.getFinalPanel());
//                this.add(this.levelFive.getGifPanel());

            levelFive.addOptionSelectionListener(selectedOption -> {
                if (selectedOption == 0 || selectedOption == JOptionPane.CLOSED_OPTION) {
                    this.levelFive.getSceneSound().stopPlay();
                    this.remove(levelFive);
                    lobbyPanel.setVisible(true);
                    this.lobbyBackground.restartSound();

                } else if (selectedOption == 1) {
                    if (levelFive.isFailed()) {
                        this.levelFive.getSceneSound().stopPlay();
                        this.remove(levelFive);
                        this.lobbyPanel.getLevelButton5().doClick();
                    }
                }
            });
            levelFive.setVisible(true);
            levelFive.setFocusable(true);
            levelFive.requestFocus();
            levelFive.requestFocusInWindow();

            levelFive.getFinalPanel().getLobbyButton().addActionListener(_ -> {
                levelFive.setVisible(false);
                lobbyPanel.setVisible(true);
                this.lobbyBackground.restartSound();

                levelFive.getFinalPanel().getLobbyButton().setVisible(false);
                levelFive.remove(levelFive.getFinalPanel());
                this.remove(levelFive.getFinalPanel());
                this.remove(levelFive);

            });
        });


    }


    private void addExitLobbyListener() {
        this.lobbyPanel.getExitButton().addActionListener(_ -> {
            this.lobbyPanel.setVisible(false);
            this.remove(lobbyPanel);
            this.signInPanel.setVisible(true);
        });
    }

    public void showWindow() {
        this.setVisible(true);
    }

    public int getWidth() {
        return windowWidth;
    }

    public int getHeight() {
        return windowHeight;
    }
}
