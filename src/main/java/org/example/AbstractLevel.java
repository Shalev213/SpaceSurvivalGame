package org.example;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractLevel extends JPanel {

    protected int width;
    protected int height;
    protected ImageIcon spaceBackgroundOne;
    protected ImageIcon spaceBackgroundTwo;
    protected int xOfBackgroundOne = 0;
    protected int xOfBackgroundTwo;

    public AbstractLevel() {
        this.setSize(this.width, this.height);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void mainGameLoop() {
        new Thread(() -> {
            while (true) {
                repaint();
                backgroundLoop();
//                gameScene();

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
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
//    public abstract  void paintComponent();
}
