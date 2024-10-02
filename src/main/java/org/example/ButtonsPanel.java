package org.example;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class ButtonsPanel extends JPanel {
    private final int panelWidth;
    private final int panelHeight;
    private ImageIcon riddleBackground;
//    private int x = 0;
//    private int y = 0;
    private JButton exitButton;
    private JButton hintsButton;
    private int returnButtonWidth = 80;
    private ColorButton colorButton1;
    private ColorButton colorButton2;
    private ColorButton colorButton3;
    private ColorButton colorButton4;
    private ColorButton colorButton5;
    private ColorButton colorButton6;
    private int spaceBetweenButtons = 7;
//    private JLabel hint1;
    private HintsPanel hintsPanel;

    public ButtonsPanel(){
//        this.setBackground(Color.green);
        this.riddleBackground = new ImageIcon("src/main/java/resources/RiddleBackground.png");
//        this.riddleBackground = new ImageIcon("src/main/java/resources/hintsBackground.png");
        this.panelWidth = this.riddleBackground.getIconWidth();
        this.panelHeight = this.riddleBackground.getIconHeight();


        this.setSize(panelWidth, panelHeight);
        this.setLayout(null);
        this.setVisible(false);


        this.colorButton1 = new ColorButton(88, 0);
        this.colorButton2 = new ColorButton((int) (this.colorButton1.getY() + this.colorButton1.getHeight() + this.spaceBetweenButtons), 1);
        this.colorButton3 = new ColorButton((int) (this.colorButton2.getY() + this.colorButton2.getHeight() + this.spaceBetweenButtons), 2);
        this.colorButton4 = new ColorButton((int) (this.colorButton3.getY() + this.colorButton3.getHeight() + this.spaceBetweenButtons), 3);
        this.colorButton5 = new ColorButton((int) (this.colorButton4.getY() + this.colorButton4.getHeight()) + this.spaceBetweenButtons, 4);
        this.colorButton6 = new ColorButton((int) (this.colorButton5.getY() + this.colorButton5.getHeight() + this.spaceBetweenButtons), 5);

        this.add(colorButton1);
        this.add(colorButton2);
        this.add(colorButton3);
        this.add(colorButton4);
        this.add(colorButton5);
        this.add(colorButton6);



        this.hintsButton = new JButton("hints");
        this.hintsButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.hintsButton.setBounds((this.panelWidth - this.returnButtonWidth) / 11, 135, this.returnButtonWidth,40);
        this.hintsButton.setFocusPainted(false);
        this.hintsButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.hintsButton.setBackground(new Color(176,224,219));
        this.hintsButton.setForeground(new Color(0,0,0));

        this.add(hintsButton);


        this.exitButton = new JButton("exit");
        this.exitButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.exitButton.setBounds((this.panelWidth - this.returnButtonWidth) / 11, 225, this.returnButtonWidth,40);
        this.exitButton.setFocusPainted(false);
        this.exitButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.exitButton.setBackground(new Color(176,224,219));
        this.exitButton.setForeground(new Color(0,0,0));

        this.exitButton.addActionListener(e -> {
            this.setVisible(false);
        });

        this.add(exitButton);

    }
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (riddleBackground != null){
            this.riddleBackground.paintIcon(null, graphics, 0, 0);
        }

    }

//    public void setX(int x) {
//        this.x = x;
//    }
//
//    public void setY(int y) {
//        this.y = y;
//    }

    public JButton getHintsButton() {
        return hintsButton;
    }
    public void hidePanel(){
        this.colorButton1.setVisible(false);
        this.colorButton2.setVisible(false);
        this.colorButton3.setVisible(false);
        this.colorButton4.setVisible(false);
        this.colorButton5.setVisible(false);
        this.colorButton6.setVisible(false);
        this.exitButton.setVisible(false);
        this.hintsPanel.setVisible(false);
    }

    public JButton getExitButton() {
        return exitButton;
    }
}