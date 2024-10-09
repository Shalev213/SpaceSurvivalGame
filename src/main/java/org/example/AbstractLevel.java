package org.example;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractLevel extends JPanel {

    protected int windowWidth = 1100;
    protected int windowHeight = 750;
    protected ImageIcon spaceBackgroundOne;
    protected ImageIcon spaceBackgroundTwo;
    protected int xOfBackgroundOne = 0;
    protected int xOfBackgroundTwo;
    protected boolean gameCondition = true;

    public AbstractLevel() {
        this.setSize(this.windowWidth, this.windowHeight);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void mainGameLoop() {
        new Thread(() -> {
            while (gameCondition) {
//                backgroundLoop();
                gameScene();
                repaint();

                try {
                    Thread.sleep(7);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            gameOver();
        }).start();
    }
    public void moveBackgrounds() {
        this.xOfBackgroundOne -= 1;
        this.xOfBackgroundTwo -= 1;
    }
    public void backgroundLoop() {
        moveBackgrounds();
        if (xOfBackgroundOne <= -(getBackgroundWidth())) {
            xOfBackgroundOne = getBackgroundWidth() - 5;
        }
        if (xOfBackgroundTwo <= -(getBackgroundWidth())) {
            xOfBackgroundTwo = getBackgroundWidth() - 5;
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (spaceBackgroundOne != null && spaceBackgroundTwo != null) {
            this.spaceBackgroundOne.paintIcon(null, graphics, xOfBackgroundOne, 0);
            this.spaceBackgroundTwo.paintIcon(null, graphics, xOfBackgroundTwo, 0);
        }
    }

    public abstract int getBackgroundWidth();

    public abstract void gameScene();
    public abstract void gameOver();
//    public abstract  void paintComponent();




}
