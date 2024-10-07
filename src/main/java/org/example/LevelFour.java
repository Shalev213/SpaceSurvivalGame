package org.example;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LevelFour extends AbstractLevel implements KeyListener {
    private ImageIcon spaceshipBackground;
    private Astronaut astronaut;
    private int xOfBackground = 0;
    private boolean rightPressed = false;
    private boolean leftPressed = false;
    private int fakeButtonWidth = 200;
    private int xOfAstronaut;
    private final JButton circuitButton;
    private final JButton fakeButton;
    private int xOfCircuit;
    private int yOfCircuit;
    private CircuitBreakerOne circuitBreaker1;





    public LevelFour(int width, int height) {
        super.windowWidth = width;
        super.windowHeight = height;

        circuitBreaker1 = new CircuitBreakerOne();
        this.xOfCircuit = (this.windowWidth - this.circuitBreaker1.getWidth()) / 2;
        this.yOfCircuit = (this.windowHeight - this.circuitBreaker1.getHeight()) / 2;
        this.circuitBreaker1.setBounds(this.xOfCircuit, this.yOfCircuit, this.circuitBreaker1.getWidth(), this.circuitBreaker1.getHeight());
        this.add(circuitBreaker1);

        this.astronaut = new Astronaut();
        this.astronaut.setY(((windowHeight - this.astronaut.getHeight()) / 2) + 60);
        this.xOfAstronaut = (int) ((windowWidth - this.astronaut.getWidth()) / 2);
        this.astronaut.setX(xOfAstronaut);


        this.spaceBackgroundOne = null;
        this.spaceBackgroundTwo = null;
        this.spaceshipBackground = new ImageIcon("src/main/java/resources/levelTwoBackground.png");
        this.xOfBackground = -(this.spaceshipBackground.getIconWidth() - this.windowWidth) / 2;


        this.fakeButton = new JButton("?");
        this.fakeButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.fakeButton.setContentAreaFilled(false);
        this.fakeButton.setBorderPainted(false);
        this.fakeButton.setFocusPainted(false);
        this.fakeButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.fakeButton.setFocusable(false);
        this.fakeButton.setForeground(new Color(142, 109, 4, 224));


        this.fakeButton.addActionListener(e -> {
            System.out.println("hahahaha you clicked on: fakeRiddleButton");

        });
        this.add(fakeButton);


        this.circuitButton = new JButton("click here");
        this.circuitButton.setFont(new Font("Arial", Font.BOLD, 30));
        this.circuitButton.setContentAreaFilled(false);
        this.circuitButton.setBorderPainted(false);
        this.circuitButton.setFocusPainted(false);
        this.circuitButton.setFocusable(false);
        this.circuitButton.setForeground(new Color(60, 124, 144, 255));
        this.circuitButton.addActionListener(e -> {
            System.out.println("you clicked on: showRiddleButton");

            this.circuitBreaker1.setVisible(true);
            this.circuitBreaker1.setFocusable(true);
            this.circuitBreaker1.requestFocus();
            this.circuitBreaker1.requestFocusInWindow();
        });
        this.add(circuitButton);






        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
        this.requestFocusInWindow();
        mainGameLoop();

    }


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (spaceshipBackground != null) {
            this.spaceshipBackground.paintIcon(null, graphics, xOfBackground, 0);
            this.fakeButton.setBounds(xOfBackground + 1320,140,28,45);
            this.circuitButton.setBounds(xOfBackground + (spaceshipBackground.getIconWidth() - this.fakeButtonWidth) / 2,260,this.fakeButtonWidth,45);
        }
        astronaut.paintAstronaut(graphics);
    }



    @Override
    public void gameScene() {
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

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

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

    @Override
    public int getBackgroundWidth() {
        return spaceshipBackground.getIconWidth();
    }


    public void takeBackgroundRight() {
        this.xOfBackground += 1;
    }
    public void takeBackgroundLeft() {
        this.xOfBackground -= 1;
    }

}
