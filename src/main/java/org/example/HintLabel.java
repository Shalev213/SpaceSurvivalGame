package org.example;

import javax.swing.*;
import java.awt.*;

public class HintLabel extends JLabel {
    private int y;
    private int x = 80;
    private String text;

    public HintLabel(String text, int y){
        this.text = text;
        this.y = y;

        this.setText(this.text);
        this.setFont(new Font("Arial", Font.ITALIC , 20));
        this.setForeground(new Color(98, 205, 204));
        this.setBounds(this.x, this.y, 750, 40);
    }
}
