package org.example;

import javax.swing.*;
import java.awt.*;

public class LevelThree extends AbstractLevel {

    public LevelThree(int width, int height) {
        // אתחול הרקעים קודם לקריאה לבנאי של המחלקה האבסטרקטית
        this.spaceBackgroundOne = new ImageIcon("src/main/java/resources/LevelThree.png");
        this.spaceBackgroundTwo = new ImageIcon("src/main/java/resources/LevelThreeMirror.png");
        // קריאה לבנאי של המחלקה האבסטרקטית אחרי אתחול הרקעים
        super.windowWidth = width;
        super.windowHeight = height;
        super.xOfBackgroundTwo = this.spaceBackgroundOne.getIconWidth();


        mainGameLoop();
    }

    @Override
    public void paintComponent(Graphics graphics) {

    }

    @Override
    public int getBackgroundWidth() {
        return spaceBackgroundOne.getIconWidth();
    }

    @Override
    public void gameScene() {

    }

    @Override
    public void gameOver() {

    }
}



