package org.example;

import javax.swing.*;
import java.awt.*;

public class InstructionsButton extends JButton{

    private String astronautToRightPath = "src/main/java/resources/astronaut_with_sign_right.png";
    private String astronautToLeftPath = "src/main/java/resources/astronaut_with_sign.png";

    private ImageIcon astronautToRightImage;
    private ImageIcon astronautToLeftImage;
    private final int width = 200;
    private final int height = 200;
    private int y;
    private double x = 40;
    private int leftXLimit;
    private int rightXLimit;
    private boolean isRightDirection = true;
    private boolean isMove = true;
//    private final ImageIcon tankIcon1 = new ImageIcon("src/main/resources/Tank.png");



    public InstructionsButton(int leftXLimit, int rightXLimit) {
        this.leftXLimit = leftXLimit;
        this.rightXLimit = rightXLimit;
        this.x = 200;
        this.y = 600 - this.height - 120;

        this.astronautToRightImage = new ImageIcon(astronautToRightPath);
        this.astronautToLeftImage = new ImageIcon(astronautToLeftPath);
        this.astronautToRightImage.setImage(this.astronautToRightImage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        this.astronautToLeftImage.setImage(this.astronautToLeftImage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));

        this.setIcon(astronautToRightImage);
        this.setBounds((int) x, y, width, height);// מגדיר מיקום וגודל לכפתור
        this.setContentAreaFilled(false);
        this.setBorder(null);

        this.addActionListener(_ -> {

            System.out.println("workingggggg");
        });


        autoMove();

    }


    public void paintAstronaut() {
        this.setBounds((int) x, y, width, height);// מגדיר מיקום וגודל לכפתור
        if (isRightDirection){
            this.setIcon(astronautToRightImage);
//            this.astronautToRightImage.setImage(astronautToRightPath);
//            graphics.drawImage(this.astronautToRightImage, this.x, this.y, this.width, this.height, null);
        } else {
            this.setIcon(astronautToLeftImage);
//            graphics.drawImage(this.astronautToLeftImage, this.x, this.y, this.width, this.height, null);
        }
    }


    public void autoMove(){

        new Thread(() -> {

            while (isMove){

                if (this.x < this.rightXLimit && this.isRightDirection){
//                    isRightDirection = true;
                    this.x += 0.2;
                } else if (this.x >= this.rightXLimit){
                    isRightDirection = false;
                }
                if (this.x > this.leftXLimit && !this.isRightDirection){
//                    isRightDirection = false;
                    this.x -= 0.2;
                } else if (this.x <= this.leftXLimit) {
                    isRightDirection = true;
                }




                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                repaint();

            }

        }).start();
    }



//    public double getX() {
//        return this.x;
//    }

    public int getY() {
        return y;
    }

    public boolean isMove() {
        return isMove;
    }


}
