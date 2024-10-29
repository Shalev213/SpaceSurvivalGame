package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class LevelFive extends AbstractLevel implements KeyListener {


    private ImageIcon background;
    private AlienSpaceship alienSpaceship1;
    private AlienSpaceship alienSpaceship2;
    private List<AlienSpaceship> alienSpaceships;

    private Astronaut astronaut1;
    private Astronaut astronaut2;

    private Alien alien1;
    private Alien alien2;
    private Alien alien3;

    private List<Alien> aliens;


    private boolean downPressed = false;
    private boolean upPressed = false;
    private boolean rightPressed = false;
    private boolean leftPressed = false;
    private boolean wPressed = false;
    private boolean sPressed = false;
    private boolean aPressed = false;
    private boolean dPressed = false;
    private boolean spacePressed = false;
    private boolean enterPressed = false;
    private Laser laser1;
    private Laser laser2;
    private boolean laser1Move = false;
    private boolean laser2Move = false;
    private boolean laser1Colision = false;
    private boolean laser2Colision = false;


    private Object[] options;
    private int selectedOption;
    private List<LevelThree.OptionSelectionListener> listeners = new ArrayList<>(); // רשימת מאזינים


    private boolean alienHasCollision = false;
    private byte alienIndex;
    private int counterOfMisses = 0;
    private boolean hasMisses = false;
    private int counterOfAlienHits = 0;



    public LevelFive(int width, int height){
        this.background = new ImageIcon("src/main/java/resources/backroundLevelFive.png");

        super.windowWidth = width;
        super.windowHeight = height;

        this.astronaut1 = new Astronaut();
        this.astronaut2 = new Astronaut();

        this.alienSpaceship1 = new AlienSpaceship("src/main/java/resources/AlienSpaceship1.png");
        this.alienSpaceship1.setRandomX(this.windowWidth, this.windowWidth * 2);
        this.alienSpaceship1.setRandomY(0, this.windowHeight / 4 - this.alienSpaceship1.getHeight());
        this.alienSpaceship1.start();

//        this.alienSpaceship2 = new AlienSpaceship("src/main/java/resources/AlienSpaceship2.png");
//        this.alienSpaceship2.setRandomX(this.windowWidth, this.windowWidth * 2);
//        this.alienSpaceship2.setRandomY(0, this.windowHeight / 2 - this.alienSpaceship2.getHeight() - 30);
//        this.alienSpaceship2.start();


        this.alienSpaceships = new ArrayList<>();
        this.alienSpaceships.add(alienSpaceship1);
//        this.alienSpaceships.add(alienSpaceship2);

        this.alien1 = new Alien("src/main/java/resources/hybridAlienToLeft.png","src/main/java/resources/hybridAlienToRight.png");
        this.alien1.setWalkingRight(false);
        this.alien1.setRandomX();
        this.alien1.start();


        this.alien2 = new Alien("src/main/java/resources/ordinaryAlien1ToLeft.png" , "src/main/java/resources/ordinaryAlien1ToRight.png");
        this.alien2.setRandomX();
        this.alien2.start();

        this.alien3 = new Alien("src/main/java/resources/ordinaryAlien2ToLeft.png", "src/main/java/resources/ordinaryAlien2ToRight.png");
        this.alien3.setRandomX();
        this.alien3.start();


        this.options = new Object[]{"Lobby", ""};

        aliens = new ArrayList<>();
        aliens.add(alien1);
        aliens.add(alien2);
        aliens.add(alien3);




        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
        this.requestFocusInWindow();
        mainGameLoop();


    }


//    public void showSuccessDialog() {
//        this.options[1] = "Next level";
//
//        this.selectedOption = JOptionPane.showOptionDialog(null,
//                "Mission Complete",
//                null,
//                JOptionPane.DEFAULT_OPTION,
//                JOptionPane.INFORMATION_MESSAGE,
//                null,
//                options,
//                options[0]);
//
//        // מפעיל את כל המאזינים עם התוצאה שנבחרה
//        notifyListeners(selectedOption);
//    }
//
//    public void showFailedDialog() {
//        this.options[1] = "Play again";
////        this.options[3] = "Lobby";
//
//        this.selectedOption = JOptionPane.showOptionDialog(null,
//                "Mission Failed",
//                null,
//                JOptionPane.DEFAULT_OPTION,
//                JOptionPane.INFORMATION_MESSAGE,
//                null,
//                options,
//                options[0]);
//
//        // מפעיל את כל המאזינים עם התוצאה שנבחרה
//        notifyListeners(selectedOption);
//
//    }
//
//
//
//
//
//
//    public interface OptionSelectionListener {
//        void onOptionSelected(int selectedOption);
//    }
//
//
//    public void addOptionSelectionListener(LevelThree.OptionSelectionListener listener) {
//        listeners.add(listener);
//    }
//
//
//    private void notifyListeners(int selectedOption) {
//        for (LevelThree.OptionSelectionListener listener : listeners) {
//            listener.onOptionSelected(selectedOption);
//        }
//    }




    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (this.background != null) {
            this.background.paintIcon(null, graphics, xOfBackgroundOne, 0);
        }
        alienSpaceship1.paintAlienSpaceship(graphics);
//        alienSpaceship2.paintAlienSpaceship(graphics);

        astronaut1.paintAstronaut(graphics);
        astronaut2.paintAstronaut(graphics);

        alien1.paintAlien(graphics);
        alien2.paintAlien(graphics);


//        laser1.paintLaser(graphics);
//        laser2.paintLaser(graphics);

    }



        public int getBackgroundWidth() {
        return spaceBackgroundOne.getIconWidth();
    }



    public void gameScene() {


        if (downPressed && this.astronaut1.getY() <= (windowHeight - 1.5 * astronaut1.getHeight())) {
            astronaut1.upDownMove(1);
        }
        if (upPressed && this.astronaut1.getY() >= 0) {
            astronaut1.upDownMove(-1);
        }
        if (rightPressed && this.astronaut1.getX() <= (this.windowWidth - 1.2 * astronaut1.getWidth())) {
            astronaut1.leftRightMove(1);
        }
        if (leftPressed && this.astronaut1.getX() >= 0) {
            astronaut1.leftRightMove(-2);
        }




        if (sPressed && this.astronaut2.getY() <= (windowHeight - 1.75 * astronaut2.getHeight())) {
            astronaut2.upDownMove(1);
        }
        if (wPressed && this.astronaut2.getY() >= 0) {
            astronaut2.upDownMove(-1);
        }
        if (dPressed && this.astronaut2.getX() <= (this.windowWidth - 1.2 * astronaut2.getWidth())) {
            astronaut2.leftRightMove(1);
        }
        if (aPressed && this.astronaut2.getX() >= 0) {
            astronaut2.leftRightMove(-2);
        }


//        if (this.laser1.getX() == this.astronaut1.getX() + 65 && enterPressed){
//            this.laserShot1.startPlay();
//        }
//        if (this.laser2.getX() == this.astronaut2.getX() + 65 && spacePressed){
//            this.laserShot2.startPlay();
//        }

        repaint();


        alienSpaceshipsLoop();
        checkAliensCollision();



    }






    public void gameOver() {

    }






    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN -> downPressed = true;
            case KeyEvent.VK_UP -> upPressed = true;
            case KeyEvent.VK_RIGHT -> rightPressed = true;
            case KeyEvent.VK_LEFT -> leftPressed = true;
            case KeyEvent.VK_W -> wPressed = true;
            case KeyEvent.VK_S -> sPressed = true;
            case KeyEvent.VK_A -> aPressed = true;
            case KeyEvent.VK_D -> dPressed = true;
            case KeyEvent.VK_SPACE -> {
//                laserShot2.startPlay();
                spacePressed = true;
                laser2Move = true;
            }
            case KeyEvent.VK_ENTER -> {
//                laserShot1.startPlay();
                enterPressed = true;
                laser1Move = true;
            }
        }

    }


    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN -> downPressed = false;
            case KeyEvent.VK_UP -> upPressed = false;
            case KeyEvent.VK_RIGHT -> rightPressed = false;
            case KeyEvent.VK_LEFT -> leftPressed = false;
            case KeyEvent.VK_W -> wPressed = false;
            case KeyEvent.VK_S -> sPressed = false;
            case KeyEvent.VK_A -> aPressed = false;
            case KeyEvent.VK_D -> dPressed = false;
            case KeyEvent.VK_SPACE -> spacePressed = false;
            case KeyEvent.VK_ENTER -> enterPressed = false;

        }
    }

    public void keyTyped(KeyEvent e) {

    }



    public void alienSpaceshipsLoop() {
        for (int i = 0; i < alienSpaceships.size(); i++) {
            if (alienSpaceships.get(i).getX() < -alienSpaceships.get(i).getWidth()) {
                alienSpaceships.get(i).setRandomX(this.windowWidth, this.windowWidth + 600);
                alienSpaceships.get(i).setRandomY(0, this.windowHeight / 2 - this.alienSpaceship1.getHeight() - 30);
            }
        }
    }



    public void aliensLoop() {
        for (int i = 0; i < aliens.size(); i++) {
            if (aliens.get(i).getX() < -aliens.get(i).getWidth()) {
                aliens.get(alienIndex).setWalkingRight(!aliens.get(alienIndex).isWalkingRight());
                aliens.get(alienIndex).setRandomX();
            }
        }
    }


    public void checkAliensCollision() {

        for (int i = 0; i < aliens.size() ; i++) {
            if (this.aliens.get(i).rectangle().intersects(this.astronaut1.rectangle()) || this.aliens.get(i).rectangle().intersects(this.astronaut2.rectangle())) {
                counterOfMisses++;
                alienHasCollision = true;
                alienIndex = (byte) i;
            }
//            else if (this.aliens.get(i).rectangle().intersects(this.laser1.rectangle())) {
//                alienHasCollision = true;
//                alienIndex = (byte) i;
//                laser1Colision = true;
//                counterOfAlienHits++;

//            } else if (this.alienSpaceships.get(i).rectangle().intersects(this.laser2.rectangle())) {
//                alienHasCollision = true;
//                alienIndex = (byte) i;
//                laser2Colision = true;
//                counterOfAlienHits++;
//            }
        }

        if (alienHasCollision){
            aliens.get(alienIndex).setWalkingRight(!aliens.get(alienIndex).isWalkingRight());
            aliens.get(alienIndex).setRandomX();
            alienHasCollision = false;

        }

//        if (laser1Colision) {
//            this.laser1.setX(laser1X);
//            this.laser1.setY(laser1Y);
//
//            laser1Move = false;
//            laser1Colision = false;
//        }
//
//        if (laser2Colision) {
//            this.laser2.setX(laser2X);
//            this.laser2.setY(laser2Y);
//
//            laser2Move = false;
//            laser2Colision = false;
//        }

//        if (hasMisses){
//            alienSpaceships.get(alienIndex).setRandomX(this.windowWidth, this.windowWidth + 600);
//            alienSpaceships.get(alienIndex).setRandomY(0, this.windowHeight - this.alienSpaceship1.getHeight());
//            hasMisses = false;
//        }

//        if (counterOfMisses == 3){
////            explosion.startPlay();
//        }
    }



















    }
