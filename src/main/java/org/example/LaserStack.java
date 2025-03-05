package org.example;

import javax.swing.*;
import java.awt.*;

public class LaserStack {

    private Image laserLogo;
    private String laserPath = "src/main/java/resources/bullet-logo.png";
    private int xOfLogo = 20;
    private int yOfLogo = 20;
    private final int widthOfLogo = 100;
    private final int heightOfLogo = 60;
    private final int widthOfLabel = 50;
    private final int heightOfLabel = 60;
    private JLabel laserCount;
    private int offset = 10;
//    private int count = 0;


    public LaserStack() {
        this.laserLogo = new ImageIcon(laserPath).getImage();

        this.laserCount = new JLabel("15");
        this.laserCount.setBounds(xOfLogo + widthOfLogo + this.offset, this.yOfLogo, this.widthOfLabel, this.heightOfLabel);

        this.laserCount.setFont(new Font("Arial", Font.BOLD, 30));
        this.laserCount.setForeground(Color.white);
    }

    public void paintBullet(Graphics graphics) {
        graphics.drawImage(this.laserLogo, this.xOfLogo, this.yOfLogo, this.widthOfLogo, this.heightOfLogo, null);
    }

    public void setX(int x) {
        this.xOfLogo = x;
    }

    public int getLogoWidth() {
        return this.widthOfLogo;
    }

    public int getWidthOfLabel() {
        return widthOfLabel;
    }

    public int getHeightOfLabel() {
        return heightOfLabel;
    }

    public int getyOfLogo() {
        return yOfLogo;
    }

    public int getXOfLogo(){
        return this.xOfLogo;
    }

//    public void setCount(int count){
//        this.count = count;
//    }

    public JLabel laserCount() {
        return laserCount;
    }

}
