package org.example;

import db.JDBC;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LevelTwo extends AbstractLevel implements KeyListener {
    private final JButton riddleButton;
    private final JButton fakeRiddleButton;
    private int xOfFakePanel;
    private int yOfFakePanel;
    private ImageIcon spaceshipBackground;
    private FlyingAstronaut astronaut;
    private int xOfBackground = 0;
    private boolean rightPressed = false;
    private boolean leftPressed = false;
    private int fakeButtonWidth = 200;
    private int xOfAstronaut;
    private ButtonsPanel buttonsPanel;
    private int xOfRiddlePanel;
    private int yOfRiddlePanel;
    private MainRiddlePanel mainRiddlePanel;
    private Sound sceneSound;
    private FakePanel fakePanel;
    private Sound laughSound;


    public LevelTwo(int width, int height, String teamName) {
//        this.teamName = teamName;
        // קריאה לבנאי של המחלקה האבסטרקטית אחרי אתחול הרקעים
        super.windowWidth = width;
        super.windowHeight = height;

        this.astronaut = new FlyingAstronaut();
        this.astronaut.setY(((windowHeight - this.astronaut.getHeight()) / 2) + 60);
        this.xOfAstronaut = (int) ((windowWidth - this.astronaut.getWidth()) / 2);
        this.astronaut.setX(xOfAstronaut);
        // אתחול הרקעים קודם לקריאה לבנאי של המחלקה האבסטרקטית
        this.spaceBackgroundOne = null;
        this.spaceBackgroundTwo = null;
        this.spaceshipBackground = new ImageIcon("src/main/java/resources/levelTwoBackground.png");
        this.xOfBackground = -(this.spaceshipBackground.getIconWidth() - this.windowWidth) / 2;


        this.mainRiddlePanel = new MainRiddlePanel();
        this.xOfRiddlePanel = (this.windowWidth - this.mainRiddlePanel.getWidth()) / 2;
        this.yOfRiddlePanel = (this.windowHeight - this.mainRiddlePanel.getHeight()) / 2;
        this.mainRiddlePanel.setBounds(this.xOfRiddlePanel, this.yOfRiddlePanel, this.mainRiddlePanel.getWidth(), this.mainRiddlePanel.getHeight());

        this.add(mainRiddlePanel);


        this.riddleButton = new JButton("?");
        this.riddleButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.riddleButton.setContentAreaFilled(false);
        this.riddleButton.setBorderPainted(false);
        this.riddleButton.setFocusPainted(false);
        this.riddleButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.riddleButton.setFocusable(false);
        this.riddleButton.setForeground(new Color(142, 109, 4, 150));

        this.riddleButton.addActionListener(e -> {
            this.mainRiddlePanel.setVisible(true);
            this.mainRiddlePanel.setFocusable(true);
            this.mainRiddlePanel.getButtonsPanel().setVisible(true);
            this.mainRiddlePanel.requestFocus();
            this.mainRiddlePanel.requestFocusInWindow();
        });

        this.add(riddleButton);


        this.fakeRiddleButton = new JButton("click here");
        this.fakeRiddleButton.setFont(new Font("Arial", Font.BOLD, 30));
        this.fakeRiddleButton.setContentAreaFilled(false);
        this.fakeRiddleButton.setBorderPainted(false);
        this.fakeRiddleButton.setFocusPainted(false);
        this.fakeRiddleButton.setFocusable(false);
        this.fakeRiddleButton.setForeground(new Color(60, 124, 144, 255));

        this.add(fakeRiddleButton);

        this.fakePanel = new FakePanel();
        this.xOfFakePanel = (this.windowWidth - this.fakePanel.getWidth()) / 2;
        this.yOfFakePanel = (this.windowHeight - this.fakePanel.getHeight()) / 2;
        this.fakePanel.setBounds(this.xOfFakePanel, this.yOfFakePanel, this.fakePanel.getWidth(), this.fakePanel.getHeight());

        this.add(this.fakePanel);
        this.setComponentZOrder(fakePanel, 0);


        this.laughSound = new Sound();
        this.laughSound.playSound("src/main/java/resources/evil-laughter.wav");

        this.fakeRiddleButton.addActionListener(e -> {
            this.laughSound.startPlay();
            System.out.println("hahahaha you clicked on: fakeRiddleButton");
            this.fakeRiddleButton.setEnabled(false);
            this.fakePanel.setVisible(true);
            this.fakePanel.setFocusable(true);
            this.fakePanel.requestFocus();
            this.fakePanel.requestFocusInWindow();
        });

        this.fakePanel.getExitButton().addActionListener(e -> {
            this.fakePanel.setVisible(false);
            this.fakeRiddleButton.setEnabled(true);

        });


        this.sceneSound = new Sound();
        this.sceneSound.playSound("src/main/java/resources/spaceship-alarm.wav");

        this.mainRiddlePanel.getButtonsPanel().getCheckButton().addActionListener(e -> {
            if (mainRiddlePanel.getButtonsPanel().isSuccess()){
                JDBC.updateLevel(teamName, 3);
                mainRiddlePanel.getButtonsPanel().success();
                this.sceneSound.stopPlay();

            }else {
               this.mainRiddlePanel.getButtonsPanel().failure();
            }
        });


        this.setFocusable(true);
        this.addKeyListener(this);
        this.requestFocus();
        this.requestFocusInWindow();

        mainGameLoop();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (spaceshipBackground != null) {
            this.spaceshipBackground.paintIcon(null, graphics, xOfBackground, 0);
            this.riddleButton.setBounds(xOfBackground + 1320,140,28,45);
            this.fakeRiddleButton.setBounds(xOfBackground + (spaceshipBackground.getIconWidth() - this.fakeButtonWidth) / 2,260,this.fakeButtonWidth,45);
        }
        astronaut.paintAstronaut(graphics);
    }

    @Override
    public int getBackgroundWidth() {
        return spaceshipBackground.getIconWidth();
    }

    @Override
    public void gameScene() {
        this.sceneSound.startBackgroundPlay();
        this.sceneSound.loopPlay();

        if (rightPressed && (this.xOfBackground + this.getBackgroundWidth()) > this.windowWidth && !(xOfAstronaut > this.astronaut.getX()) ) {
            takeBackgroundLeft();
        } else if (rightPressed && (this.astronaut.getX() + this.astronaut.getWidth()) < this.windowWidth) {
            this.astronaut.leftRightMove(1);
        }
        if (leftPressed && this.xOfBackground < 0 && !(xOfAstronaut < this.astronaut.getX())) {
            takeBackgroundRight();
        }else if (leftPressed && this.astronaut.getX() > 0) {
            this.astronaut.leftRightMove(-1);
        }
        repaint();
    }

    @Override
    public void gameOver() {
        this.sceneSound.stopPlay();
    }
    public void takeBackgroundRight() {
        this.xOfBackground += 1;
    }
    public void takeBackgroundLeft() {
        this.xOfBackground -= 1;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT -> {
                this.rightPressed = true;
                this.astronaut.setMirrorChosen(false);
            }
            case KeyEvent.VK_LEFT -> {
                this.leftPressed = true;
                this.astronaut.setMirrorChosen(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT -> this.rightPressed = false;
            case KeyEvent.VK_LEFT -> this.leftPressed = false;
        }
    }
    public JButton getNextLevelButton() {
        return this.mainRiddlePanel.getButtonsPanel().getNextLevelButton();
    }
    public JButton getLobbyButton() {
        return this.mainRiddlePanel.getButtonsPanel().getLobbyButton();
    }
    public Sound getSceneSound() {
        return sceneSound;
    }
}