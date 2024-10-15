package org.example;

import javax.swing.*;
import java.awt.*;

public class ToolsOfLife {
    private Image tool1;
    private Image tool2;
    private Image tool3;
    private final int windowWidth = 1100;
    private final int windowHeight = 750;
    private boolean showTool1 = true;
    private boolean showTool2 = true;
    private boolean showTool3 = true;
    private final short x = (short) (windowWidth- 200);
    private final byte y = 10;
    private final byte width = 45;
    private final byte height = 40;
    private final byte space = 10;


    public ToolsOfLife(){
        this.tool1 = new ImageIcon("src/main/java/resources/tool.png").getImage();
        this.tool2 = new ImageIcon("src/main/java/resources/tool.png").getImage();
        this.tool3 = new ImageIcon("src/main/java/resources/tool.png").getImage();
    }


    public void paintTools(Graphics graphics) {
        if (showTool1) {
            graphics.drawImage(this.tool1, this.x, this.y, this.width, this.height,null);
        }
        if (showTool2) {
            graphics.drawImage(this.tool2, this.x + this.width + this.space, this.y, this.width, this.height, null);
        }
        if (showTool3) {
            graphics.drawImage(this.tool3, this.x + 2 * (this.width + this.space), this.y, this.width, this.height, null);
        }
    }


    public void hideHeart3() {
        showTool3 = false;
    }

    public void hideHeart2() {
        showTool2 = false;
    }

    public void hideHeart1() {
        showTool1 = false;
    }
}