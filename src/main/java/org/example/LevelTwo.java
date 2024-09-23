package org.example;

import javax.swing.*;

public class LevelTwo extends AbstractLevel {

    public LevelTwo(int width, int height) {
        // אתחול הרקעים קודם לקריאה לבנאי של המחלקה האבסטרקטית
        this.spaceBackgroundTwo = new ImageIcon("src/main/java/resources/LevelTwoMirror.png");
        this.spaceBackgroundOne = new ImageIcon("src/main/java/resources/LevelTwo.png");





        // קריאה לבנאי של המחלקה האבסטרקטית אחרי אתחול הרקעים
        super.windowWidth = width;
        super.windowHeight = height;
        super.xOfBackgroundTwo = this.spaceBackgroundOne.getIconWidth();

        mainGameLoop();
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