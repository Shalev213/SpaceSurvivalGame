package org.example;

import javax.swing.*;
import java.awt.*;

public class LevelTwo extends JPanel {

    private int width;
    private int height;
    private ImageIcon spaceBackgroundOne;
    private ImageIcon spaceBackgroundTwo;
    private int xOfBackgroundOne = 0;
    private int xOfBackgroundTwo;




    public LevelTwo(int width, int height) {
        this.width = width;
        this.height = height;
//        this.setBackground(Color.cyan);
        this.setSize(this.width, this.height);
        this.setLayout(null);
        this.setVisible(true);

        this.spaceBackgroundOne = new ImageIcon("src/main/java/sources/LevelTwo.png");
        this.spaceBackgroundTwo = new ImageIcon("src/main/java/sources/LevelTwoMirror.png");
        this.xOfBackgroundTwo = spaceBackgroundOne.getIconWidth();

        mainGameLoop();
    }
    public void mainGameLoop() {
        new Thread(() -> {
            while (true) {
                repaint();
//                moveBackgrounds();
                loopBackground();

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

    public void loopBackground(){
        moveBackgrounds();
        if (xOfBackgroundOne <= -(this.spaceBackgroundOne.getIconWidth())){
            xOfBackgroundOne = this.spaceBackgroundOne.getIconWidth();
        } else if (xOfBackgroundTwo <= -(this.spaceBackgroundTwo.getIconWidth())) {
            xOfBackgroundTwo = this.spaceBackgroundTwo.getIconWidth();
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        this.spaceBackgroundOne.paintIcon(null, graphics, xOfBackgroundOne, 0);
        this.spaceBackgroundTwo.paintIcon(null, graphics, xOfBackgroundTwo, 0);


    }
//    private final ImageIcon background1 = new ImageIcon("src/main/resources/GameBackground.png");

}