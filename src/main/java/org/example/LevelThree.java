package org.example;

import javax.swing.*;

public class LevelThree extends AbstractLevel {

    public LevelThree(int width, int height) {
        // אתחול הרקעים קודם לקריאה לבנאי של המחלקה האבסטרקטית
        this.spaceBackgroundOne = new ImageIcon("src/main/java/sources/LevelThree.png");
        this.spaceBackgroundTwo = new ImageIcon("src/main/java/sources/LevelThreeMirror.png");
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



