package org.example;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class RiddlePanel extends JPanel {
    private final int panelWidth = 450;
    private final int panelHeight = 600;
    private int x = 0;
    private int y = 0;
    private final JButton returnButton;
    private int returnButtonWidth = 90;
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

        this.returnButton = new JButton("return");
        this.returnButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.returnButton.setBounds((this.panelWidth - this.returnButtonWidth) / 3,620, this.returnButtonWidth,50);
        this.returnButton.setFocusPainted(false);
        this.returnButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.add(returnButton);


    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
