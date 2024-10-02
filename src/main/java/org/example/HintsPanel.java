package org.example;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class HintsPanel extends JPanel {
    private int space = 5;
    private int returnButtonHeight = 40;
    private int returnButtonWidth = 80;
    private JButton returnButton;
    private ImageIcon hintsBackground;
    private final int panelWidth;
    private final int panelHeight;
    private HintLabel hintLabel1;
    private HintLabel hintLabel2;
    private HintLabel hintLabel3;
    private HintLabel hintLabel4;
    private HintLabel hintLabel5;
    private HintLabel hintLabel6;

    public HintsPanel() {
        this.hintsBackground = new ImageIcon("src/main/java/resources/hintsBackground.png");
//        this.riddleBackground = new ImageIcon("src/main/java/resources/hintsBackground.png");
        this.panelWidth = this.hintsBackground.getIconWidth();
        this.panelHeight = this.hintsBackground.getIconHeight();

        this.setSize(panelWidth, panelHeight);
//        System.out.println("width= " + panelWidth + " ,height= " + panelHeight);
        this.setBorder(null);
        this.setLayout(null);
        this.setVisible(false);

        this.hintLabel1 = new HintLabel("<html>1) The <font color='red'>red</font> button must be immediately after the <font color='white'>white</font> button.</html>", 120);
        this.hintLabel2 = new HintLabel("<html>2) The <font color='green'>green</font> button cannot be first or last.</html>", this.hintLabel1.getY() + this.hintLabel1.getHeight() + this.space);
        this.hintLabel3 = new HintLabel("<html>3) The <font color='yellow'>yellow</font> button must be second.</html>", this.hintLabel2.getY() + this.hintLabel2.getHeight() + this.space);
        this.hintLabel4 = new HintLabel("<html>4) The <font color='purple'>purple</font> button must be before the <font color='green'>green</font> button.</html>", this.hintLabel3.getY() + this.hintLabel3.getHeight() + this.space);
        this.hintLabel5 = new HintLabel("<html>5) The <font color='blue'>blue</font> button must not be next to the <font color='yellow'>yellow</font> button.</html>", this.hintLabel4.getY() + this.hintLabel4.getHeight() + this.space);
        this.hintLabel6 = new HintLabel("<html>6) The <font color='red'>red</font> button must be one position higher than the <font color='green'>green</font> button.</html>", this.hintLabel5.getY() + this.hintLabel5.getHeight() + this.space);

        this.add(this.hintLabel1);
        this.add(this.hintLabel2);
        this.add(this.hintLabel3);
        this.add(this.hintLabel4);
        this.add(this.hintLabel5);
        this.add(this.hintLabel6);

        this.returnButton = new JButton("return");
        this.returnButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.returnButton.setBounds((this.panelWidth - this.returnButtonWidth) / 2, this.panelHeight - (2 * this.returnButtonHeight), this.returnButtonWidth,this.returnButtonHeight);
        this.returnButton.setFocusPainted(false);
        this.returnButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.returnButton.setBackground(new Color(176,224,219));
        this.returnButton.setForeground(new Color(0,0,0));


        this.add(returnButton);
    }

    public JButton getReturnButton() {
        return returnButton;
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (hintsBackground != null){
            this.hintsBackground.paintIcon(null, graphics, 0, 0);

        }
    }
}
