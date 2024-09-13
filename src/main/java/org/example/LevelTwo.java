package org.example;

import javax.swing.*;

public class LevelTwo extends AbstractLevel {

    public LevelTwo(int width, int height) {
        // אתחול הרקעים קודם לקריאה לבנאי של המחלקה האבסטרקטית
        this.spaceBackgroundTwo = new ImageIcon("src/main/java/sources/LevelTwoMirror.png");
        this.spaceBackgroundOne = new ImageIcon("src/main/java/sources/LevelTwo.png");





        // קריאה לבנאי של המחלקה האבסטרקטית אחרי אתחול הרקעים
        super.width = width;
        super.height = height;
        super.xOfBackgroundTwo = this.spaceBackgroundOne.getIconWidth();

        mainGameLoop();
    }

    @Override
    public int getBackgroundWidth() {
        return spaceBackgroundOne.getIconWidth();
    }


}