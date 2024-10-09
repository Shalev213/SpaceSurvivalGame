package org.example;

import javax.swing.*;
import java.awt.*;

public class LevelButton extends JButton {
    public LevelButton(int x, int y, String text) {
        this.setText(text);
        this.setBounds(x, y, 170, 280);
        this.setFont(new Font("Arial", Font.BOLD, 80));
        this.setFocusPainted(false);
        this.setEnabled(false);
//        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

    }
}
