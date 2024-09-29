package org.example;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class RiddlePanel extends JPanel {
    private final int panelWidth = 450;
    private final int panelHeight = 600;
    private int x = 0;
    private int y = 0;
    private JButton returnButton;
    private int returnButtonWidth = 80;
    private ColorButton colorButton1;
    private ColorButton colorButton2;
    private ColorButton colorButton3;
    private ColorButton colorButton4;
    private ColorButton colorButton5;
    private ColorButton colorButton6;


    public RiddlePanel(){
        this.setBackground(Color.green);
        this.setSize(panelWidth, panelHeight);
//        this.setBounds(this.x, this.y, this.panelWidth, this.panelHeight);
        this.setLayout(null);
        this.setVisible(false);


        this.colorButton1 = new ColorButton(13, 0);
        this.colorButton2 = new ColorButton((int) (this.colorButton1.getX() + 1.5 * this.colorButton1.getWidth()), 1);
        this.colorButton3 = new ColorButton((int) (this.colorButton2.getX() + 1.5 * this.colorButton2.getWidth()), 2);
        this.colorButton4 = new ColorButton((int) (this.colorButton3.getX() + 1.5 * this.colorButton3.getWidth()), 3);
        this.colorButton5 = new ColorButton((int) (this.colorButton4.getX() + 1.5 * this.colorButton4.getWidth()), 4);
        this.colorButton6 = new ColorButton((int) (this.colorButton5.getX() + 1.5 * this.colorButton5.getWidth()), 5);

        this.add(colorButton1);
        this.add(colorButton2);
        this.add(colorButton3);
        this.add(colorButton4);
        this.add(colorButton5);
        this.add(colorButton6);

        this.returnButton = new JButton("return");
        this.returnButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.returnButton.setBounds((this.panelWidth - this.returnButtonWidth) / 2, (int) (this.colorButton1.getY() + 1.5 * this.colorButton1.getHeight()), this.returnButtonWidth,40);
        this.returnButton.setFocusPainted(false);
        this.returnButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.returnButton.addActionListener(e -> {
            this.setVisible(false);
        });

        this.add(returnButton);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
