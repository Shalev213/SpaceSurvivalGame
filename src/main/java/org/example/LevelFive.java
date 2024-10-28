package org.example;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LevelFive extends AbstractLevel implements KeyListener {

    private Object[] options;







    public LevelFive(int width, int height){

        this.spaceBackgroundOne = new ImageIcon("");


        super.windowWidth = width;
        super.windowHeight = height;


        this.options = new Object[]{"Lobby", ""};




        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
        this.requestFocusInWindow();
        mainGameLoop();



    }



    public int getBackgroundWidth() {
        return spaceBackgroundOne.getIconWidth();
    }



    public void gameScene() {

    }



    public void gameOver() {

    }






    public void keyPressed(KeyEvent e) {

    }


    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }














    }
