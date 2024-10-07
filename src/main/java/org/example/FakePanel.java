package org.example;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class FakePanel extends JPanel {
    private final int panelWidth;
    private final int panelHeight;
    private ImageIcon fakeBackground;
    private JButton exitButton;
    private int exitButtonWidth = 80;
    private JLabel message;


    public FakePanel() {
        this.fakeBackground = new ImageIcon("src/main/java/resources/FakeBackground.png");
        this.panelWidth = this.fakeBackground.getIconWidth();
        this.panelHeight = this.fakeBackground.getIconHeight();
        this.setSize(this.panelWidth, this.panelHeight);
        this.setLayout(null);
        this.setVisible(false);
//        this.fakeBackground.setBounds(0, 0, this.panelWidth, this.panelHeight);

        this.exitButton = new JButton("exit");
        this.exitButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.exitButton.setBounds((this.panelWidth - this.exitButtonWidth) / 2, 400, this.exitButtonWidth,40);
        this.exitButton.setFocusPainted(false);
        this.exitButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.exitButton.setBackground(new Color(176,224,219));
        this.exitButton.setForeground(new Color(0,0,0));


        this.add(this.exitButton);

        this.message = new JLabel("Keep                   looking");
        this.message.setFont(new Font("Arial", Font.BOLD , 60));
        this.message.setForeground(new Color(7, 7, 4, 255));
        this.message.setBounds(50, 50, 750, 90);
        this.add(this.message);
    }
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (this.fakeBackground != null){
            this.fakeBackground.paintIcon(null, graphics, 0, 0);
        }

    }

    public JButton getExitButton() {
        return exitButton;
    }
}
