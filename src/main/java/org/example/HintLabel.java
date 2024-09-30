package org.example;

import javax.swing.*;
import java.awt.*;

public class HintLabel extends JLabel {
    private int y = 110;
    private int x;
    private String text;

    public HintLabel(String text, int x){
        this.text = text;
        this.x = x;

        this.setText(this.text);
        this.setFont(new Font("Arial", Font.ITALIC , 25));
        this.setForeground(new Color(98, 205, 204));
        this.setBounds(this.x, this.y, 300, 70);
    }
}
