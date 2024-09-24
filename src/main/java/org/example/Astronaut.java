package org.example;

import javax.swing.*;
import java.awt.*;

public class Astronaut {

    private String imagePath = "src/main/java/resources/astronaut.png";
    private String mirrorImagePath = "src/main/java/resources/astronautMirror.png";

    private Image astronautImage;
    private Image mirrorAstronautImage;
    private final short width = 280;
    private final short height = 280;
    private int y = 0;
    private int x = 40;
    private boolean isMirrorChosen = false;


    public Astronaut(){
        this.astronautImage = new ImageIcon(imagePath).getImage();
        this.mirrorAstronautImage = new ImageIcon(mirrorImagePath).getImage();
    }

    public void paintAstronaut(Graphics graphics){
        if (isMirrorChosen){
            graphics.drawImage(this.mirrorAstronautImage, this.x, this.y, this.width, this.height, null);
        }else {
            graphics.drawImage(this.astronautImage, this.x, this.y, this.width, this.height, null);
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

    public void leftRightMove(int dx) {
        this.x += dx;
    }
    public void setX(int x) {
        this.x = x;
    }

    public double getX() {
        return this.x;
    }

    public double getWidth() {
        return this.width;
    }

    public short getHeight() {
        return height;
    }

    public void setMirrorChosen(boolean mirrorChosen) {
        isMirrorChosen = mirrorChosen;
    }

    //    public void setImagePath(String imagePath) {
//        this.imagePath = imagePath;
//    }

    //    public Rectangle rectangle() {
//        return new Rectangle(this.x, this.y + 8 , this.width - 5 , this.height - 16);
//    }
}
