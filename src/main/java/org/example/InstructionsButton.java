package org.example;

import javax.swing.*;
import java.awt.*;

public class InstructionsButton {

    private String astronautToRightPath = "src/main/java/resources/astronautWithWeaponToRight.png";
    private String astronautToLeftPath = "src/main/java/resources/astronautWithWeaponToLeft.png";

    private Image astronautToRightImage;
    private Image astronautToLeftImage;
    private final int width = 110;
    private final int height = 155;
    private int y;
    private int x = 40;
    private boolean isWalkingToRight = false;



    public InstructionsButton() {

        this.astronautToRightImage = new ImageIcon(astronautToRightPath).getImage();
        this.astronautToLeftImage = new ImageIcon(astronautToLeftPath).getImage();

        this.y = 750 - this.height - 45;

    }

    public void paintAstronaut(Graphics graphics) {
        if (isWalkingToRight) {
            graphics.drawImage(this.astronautToRightImage, this.x, this.y, this.width, this.height, null);
        }else {
            graphics.drawImage(this.astronautToLeftImage, this.x, this.y, this.width, this.height, null);
        }
    }



    public int getX() {
        return this.x;
    }

    public int getY() {
        return y;
    }

}
