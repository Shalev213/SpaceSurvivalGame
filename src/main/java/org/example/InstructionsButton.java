package org.example;

import javax.swing.*;
import java.awt.*;

public class InstructionsButton extends JButton{

    private String astronautToRightPath = "src/main/java/resources/astronaut_with_sign_right.png";
    private String astronautToLeftPath = "src/main/java/resources/astronaut_with_sign.png";

    private Image astronautToRightImage;
    private Image astronautToLeftImage;
    private final int width = 110;
    private final int height = 155;
    private int y;
    private int x = 40;int leftXLimit, int rightXLimit
    private int leftXLimit;
    private int rightXLimit;
    private boolean isRightDirection = true;



    public InstructionsButton(int leftXLimit, int rightXLimit) {

        this.astronautToRightImage = new ImageIcon(astronautToRightPath).getImage();
        this.astronautToLeftImage = new ImageIcon(astronautToLeftPath).getImage();

        this.leftXLimit = leftXLimit;
        this.rightXLimit = rightXLimit;

        this.x = 100;
        this.y = 750 - this.height - 45;



    }

    public void paintAstronaut(Graphics graphics) {
        if (isRightDirection){
            graphics.drawImage(this.astronautToRightImage, this.x, this.y, this.width, this.height, null);
        } else {
            graphics.drawImage(this.astronautToLeftImage, this.x, this.y, this.width, this.height, null);
        }
    }


    public void autoMove(){

        new Thread(() -> {

            while (true){

                if (this.x < this.rightXLimit && this.isRightDirection){
//                    isRightDirection = true;
                    this.x += 1;
                } else if (this.x >= this.rightXLimit){
                    isRightDirection = false;
                }
                if (this.x > this.leftXLimit && !this.isRightDirection){
//                    isRightDirection = false;
                    this.x -= 1;
                } else if (this.x <= this.leftXLimit) {
                    isRightDirection = true;
                }


                repaint();


                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }

        }).start();
    }



    public int getX() {
        return this.x;
    }

    public int getY() {
        return y;
    }

}
