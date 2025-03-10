package org.example;

import javax.swing.*;
import java.awt.*;

public class EmptyFuels {

    private Image fuelImage1;
    private Image fuelImage2;
    private Image fuelImage3;
    private Image fuelImage4;
    private Image fuelImage5;


    private Image fuelFullImage1;
    private Image fuelFullImage2;
    private Image fuelFullImage3;
    private Image fuelFullImage4;
    private Image fuelFullImage5;

    private final int windowWidth = 1100;
    private final short width = 50;
    private final short height = 65;
    private String imageEmptyPath = "src/main/java/resources/emptyFuel.png";
    private String imageFullPath = "src/main/java/resources/Fuel.png";
    private final short x = (short) (windowWidth/2);
    private final byte y = 10;
    private final byte space = 10;
    private int counter;


    public EmptyFuels() {
        this.fuelImage1 = new ImageIcon(imageEmptyPath).getImage();
        this.fuelImage2 = new ImageIcon(imageEmptyPath).getImage();
        this.fuelImage3 = new ImageIcon(imageEmptyPath).getImage();
        this.fuelImage4 = new ImageIcon(imageEmptyPath).getImage();
        this.fuelImage5 = new ImageIcon(imageEmptyPath).getImage();

        this.fuelFullImage1 = new ImageIcon(imageFullPath).getImage();
        this.fuelFullImage2 = new ImageIcon(imageFullPath).getImage();
        this.fuelFullImage3 = new ImageIcon(imageFullPath).getImage();
        this.fuelFullImage4 = new ImageIcon(imageFullPath).getImage();
        this.fuelFullImage5 = new ImageIcon(imageFullPath).getImage();


    }


    public void paintEmptyFuels(Graphics graphics) {
        graphics.drawImage(this.fuelImage1, this.x, this.y, this.width, this.height, null);
        graphics.drawImage(this.fuelImage2, this.x + this.width + this.space, this.y, this.width, this.height, null);
        graphics.drawImage(this.fuelImage3, this.x + 2 * (this.width + this.space), this.y, this.width, this.height, null);
        graphics.drawImage(this.fuelImage4, this.x + 3 * (this.width + this.space), this.y, this.width, this.height, null);
        graphics.drawImage(this.fuelImage5, this.x + 4 * (this.width + this.space), this.y, this.width, this.height, null);

    }

    public void paintFullFuel(Graphics graphics, int num) {
        if (num>=1) {
            graphics.drawImage(this.fuelFullImage1, this.x, this.y, this.width, this.height, null);
        }
        if (num>=2) {
            graphics.drawImage(this.fuelFullImage2, this.x + this.width + this.space, this.y, this.width, this.height, null);
        }
        if (num>=3) {
            graphics.drawImage(this.fuelFullImage3, this.x + 2 * (this.width + this.space), this.y, this.width, this.height, null);
        }
        if (num>=4) {
            graphics.drawImage(this.fuelFullImage4, this.x + 3 * (this.width + this.space), this.y, this.width, this.height, null);
        }
        if (num>=5) {
            graphics.drawImage(this.fuelFullImage5, this.x + 4 * (this.width + this.space), this.y, this.width, this.height, null);
        }
    }




    public void setImagePath1(String imagePath) {
        this.imageEmptyPath = imagePath;
    }

    public void setImagePath(Image image) {
        image = null;
        image = new ImageIcon(imageFullPath).getImage();
    }

    public void checkCounter(int num) {
        this.counter = num;
    }
}
