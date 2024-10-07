package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CircuitBreakerOne extends JPanel implements KeyListener {
    private final int panelWidth = 1000;
    private final int panelHeight = 600;
    private int x = 0;
    private int y = 0;
    private ImageIcon circuitBreaker1;




    public CircuitBreakerOne() {
        this.setSize(panelWidth, panelHeight);
        this.setLayout(null);
        this.setVisible(false);

        this.circuitBreaker1 = new ImageIcon("src/main/java/resources/CircuitBreaker1.png");



        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
        this.requestFocusInWindow();

    }



    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (circuitBreaker1 != null){
            graphics.drawImage(this.circuitBreaker1.getImage(), this.x, this.y, this.panelWidth, this.panelHeight, null);
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
