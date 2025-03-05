package org.example;

import javax.swing.*;
import java.awt.*;

public class Astronaut {

    private String astronautToRightPath = "src/main/java/resources/astronautWithWeaponToRight.png";
    private String astronautToLeftPath = "src/main/java/resources/astronautWithWeaponToLeft.png";

    private Image astronautToRightImage;
    private Image astronautToLeftImage;
    private final int width = 110;
    private final int height = 155;
    private int y;
    private int x = 40;
    private boolean isWalkingToRight = false;



    public Astronaut() {
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


    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

//    public void upDownMove(int dy) {
//        this.y += dy;
//    }


    public void upDownMove(int dy) {
        this.y += dy;
    }


    public void leftRightMove(int dx) {
        this.x += dx;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return this.x;
    }

    public int getWidth() {
        return this.width;
    }

    public short getHeight() {
        return height;
    }

    public void setWalkingToRight(boolean walkingToRight) {
        isWalkingToRight = walkingToRight;
    }

    public boolean isWalkingToRight() {
        return isWalkingToRight;
    }

    //    public void setImagePath(String astronautToRightPath) {
//        this.astronautToRightPath = astronautToRightPath;
//    }

    public Rectangle rectangle() {
        return new Rectangle(this.x, this.y + 8 , this.width - 5 , this.height - 16);
    }


}
