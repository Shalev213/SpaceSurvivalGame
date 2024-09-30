package org.example;

import javax.swing.*;
import java.awt.*;

public class HintsPanel extends JPanel {
    private ImageIcon hintsBackground;
    private final int panelWidth;
    private final int panelHeight;

    public HintsPanel() {
//        this.setBackground(Color.green);
        this.hintsBackground = new ImageIcon("src/main/java/resources/hintsBackground.png");
//        this.riddleBackground = new ImageIcon("src/main/java/resources/hintsBackground.png");
        this.panelWidth = this.hintsBackground.getIconWidth();
        this.panelHeight = this.hintsBackground.getIconHeight();

        this.setSize(panelWidth, panelHeight);
        System.out.println("width= " + panelWidth + " ,height= " + panelHeight);
        this.setBorder(null);
        this.setLayout(null);
        this.setVisible(false);
    }
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (hintsBackground != null){
            this.hintsBackground.paintIcon(null, graphics, 0, 0);

        }
    }
}
