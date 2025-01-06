package org.example;

import javax.swing.*;
import java.awt.*;

public class LevelInstructions extends JPanel{
    private JLabel title;
    private JLabel textBody;

    public LevelInstructions(String title, String textBody) {
        this.title = new JLabel(title);
        this.title.setForeground(new Color(31, 191, 230));
        this.title.setFont(new Font("Arial", Font.BOLD, 60));
        this.title.setBounds( 150, 100, 350, 150);

        this.add(this.title);

        this.textBody = new JLabel();
        this.textBody.setText(textBody);
        this.textBody.setForeground(new Color(31, 191, 230));
        this.textBody.setFont(new Font("Arial", Font.BOLD, 60));
        this.textBody.setBounds( 10, 200, 550, 800);

        this.add(this.textBody);

    }
}
