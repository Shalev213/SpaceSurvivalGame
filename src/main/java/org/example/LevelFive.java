package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class LevelFive extends AbstractLevel implements KeyListener {

    private ImageIcon background;
    private AlienSpaceship alienSpaceship;
    private AlienSpaceship alienSpaceship2;
    private List<AlienSpaceship> alienSpaceships;
    private Astronaut astronaut1;
    private Astronaut astronaut2;
    private Alien alien1;
    private Alien alien2;
    private Alien alien3;
    private Alien alien4;
    private Alien alien5;
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
    private int laser1X = 0;
    private int laser1Y = 0;
    private int laser2X = 0;
    private int laser2Y = 0;
    private boolean laser1Move = false;
    private boolean laser2Move = false;
    private boolean laser1Colision = false;
    private boolean laser2Colision = false;
    private Sound laserShot1;
    private Sound laserShot2;
    private Laser alienLaser;
    private int laserAlienX = 0;
    private int laserAlienY = 0;
    private boolean laserAlienMove = false;
    private boolean astronaut1HasColision = false;
    private boolean astronaut2HasColision = false;
    private Sound laserAlienShot;
    private Object[] options;
    private int selectedOption;
    private List<LevelThree.OptionSelectionListener> listeners = new ArrayList<>(); // רשימת מאזינים
    private boolean alienHasCollision = false;
    private byte alienIndex;
    private int counterOfMisses = 0;
    private boolean hasAstronautHit = false;
    private int counterOfAlienHits = 0;
    private boolean isRightShoot1;
    private boolean isRightShoot2;
    //    private GameOverScreen gameOverScreen;
    private boolean isSuccess = false;
    private boolean isFailed = false;
    private VideoBackground videoBackground;
    //    private GifPanel gifPanel;
    private Sound sceneSound;
    private FinalPanel finalPanel;
    private Sound finalVideoMusic;


    public LevelFive(int width, int height) {
//        videoBackground = new VideoBackground();
//        gifPanel = new GifPanel("src/main/java/resources/final_video.gif", 500, 300);

        this.background = new ImageIcon("src/main/java/resources/backgroundLevelFive.png");
        this.finalPanel = new FinalPanel(width, height);

        this.finalVideoMusic = new Sound();


        super.windowWidth = width;
        super.windowHeight = height;
        super.gameCondition = !isSuccess && !isFailed;

        super.levelInstructions = new LevelInstructions(super.windowWidth, super.windowHeight, "Level 5", " ", "src/main/java/resources/level5instructions.png", 100, windowHeight - 100);

        this.sceneSound = new Sound();
        this.sceneSound.playSound("src/main/java/resources/levelFiveMusic.wav");


        this.astronaut1 = new Astronaut();
        this.astronaut1.setX(this.windowWidth / 2 + 10);
        this.astronaut1.setWalkingToRight(true);
        this.astronaut2 = new Astronaut();
        this.astronaut2.setX(this.windowWidth / 2 - this.astronaut2.getWidth() - 10);

        this.laser1X = this.astronaut1.getX() + 65;
        this.laser1Y = this.astronaut1.getY() + 37;

        this.laser2X = this.astronaut2.getX() + 65;
        this.laser2Y = this.astronaut2.getY() + 37;

        this.alienSpaceship = new AlienSpaceship("src/main/java/resources/AlienSpaceship1.png");
        this.alienSpaceship.setRandomX(this.windowWidth, this.windowWidth * 2);
        this.alienSpaceship.setRandomY(0, this.windowHeight / 4 - this.alienSpaceship.getHeight());
        this.alienSpaceship.start();
        this.alienSpaceship.randomFirePoint(50, windowWidth - 100);
//        this.alienSpaceship2 = new AlienSpaceship("src/main/java/resources/AlienSpaceship2.png");
//        this.alienSpaceship2.setRandomX(this.windowWidth, this.windowWidth * 2);
//        this.alienSpaceship2.setRandomY(0, this.windowHeight / 2 - this.alienSpaceship2.getHeight() - 30);
//        this.alienSpaceship2.start();


        this.alienSpaceships = new ArrayList<>();
        this.alienSpaceships.add(alienSpaceship);
//        this.alienSpaceships.add(alienSpaceship2);

        this.alien1 = new Alien("src/main/java/resources/hybridAlienToLeft.png", "src/main/java/resources/hybridAlienToRight.png");
        this.alien1.setWalkingRight(false);
        this.alien1.setRandomX();
        this.alien1.setHeight(250);
        this.alien1.setWidth(180);
        this.alien1.setY(this.windowHeight - this.alien1.getHeight() - 35);
        this.alien1.start();

        this.alien2 = new Alien("src/main/java/resources/ordinaryAlien1ToLeft.png", "src/main/java/resources/ordinaryAlien1ToRight.png");
        this.alien2.setRandomX();
        this.alien2.start();

        this.alien3 = new Alien("src/main/java/resources/ordinaryAlien2ToLeft.png", "src/main/java/resources/ordinaryAlien2ToRight.png");
        this.alien3.setRandomX();
        this.alien3.setHeight(135);
        this.alien3.setWidth(120);
        this.alien3.setY(this.windowHeight - this.alien3.getHeight() - 45);
        this.alien3.start();

        this.alien4 = new Alien("src/main/java/resources/ordinaryAlien1ToLeft.png", "src/main/java/resources/ordinaryAlien1ToRight.png");
        this.alien4.setRandomX();
        this.alien4.start();

        this.alien5 = new Alien("src/main/java/resources/ordinaryAlien2ToLeft.png", "src/main/java/resources/ordinaryAlien2ToRight.png");
        this.alien5.setRandomX();
        this.alien5.setHeight(135);
        this.alien5.setWidth(120);
        this.alien5.setY(this.windowHeight - this.alien5.getHeight() - 45);
        this.alien5.start();

        this.options = new Object[]{"Lobby", ""};

        aliens = new ArrayList<>();
        aliens.add(alien1);
        aliens.add(alien2);
        aliens.add(alien3);
        aliens.add(alien4);
        aliens.add(alien5);

        this.laser1 = new Laser("src/main/java/resources/YellowLaser.png");
        this.laser1.setX(laser1X);
        this.laser1.setY(laser1Y);

        this.laser2 = new Laser("src/main/java/resources/YellowLaser.png");
        this.laser2.setX(laser2X);
        this.laser2.setY(laser2Y);

        this.laserShot1 = new Sound();
        this.laserShot1.playSound("src/main/java/resources/laser-sound.wav");

        this.laserShot2 = new Sound();
        this.laserShot2.playSound("src/main/java/resources/laser-sound.wav");

        this.alienLaser = new Laser("src/main/java/resources/VerticalYellowLaser.png");
        this.alienLaser.setX(laserAlienX);
        this.alienLaser.setY(laserAlienY);


//        this.gameOverScreen = new GameOverScreen();

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

    public void showFailedDialog() {
        this.options[1] = "Play again";
//        this.options[3] = "Lobby";

        this.selectedOption = JOptionPane.showOptionDialog(null,
                "Mission Failed",
                null,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        // מפעיל את כל המאזינים עם התוצאה שנבחרה
        notifyListeners(selectedOption);

    }


    public interface OptionSelectionListener {
        void onOptionSelected(int selectedOption);
    }


    public void addOptionSelectionListener(LevelThree.OptionSelectionListener listener) {
        listeners.add(listener);
    }


    private void notifyListeners(int selectedOption) {
        for (LevelThree.OptionSelectionListener listener : listeners) {
            listener.onOptionSelected(selectedOption);
        }
    }


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (this.background != null) {
            this.background.paintIcon(null, graphics, (int) xOfBackgroundOne, 0);
        }
        alienSpaceship.paintAlienSpaceship(graphics);
//        alienSpaceship2.paintAlienSpaceship(graphics);

        alien1.paintAlien(graphics);
        alien2.paintAlien(graphics);
        alien3.paintAlien(graphics);
        alien4.paintAlien(graphics);
        alien5.paintAlien(graphics);


        laser1.paintLaser(graphics);
        laser2.paintLaser(graphics);
        alienLaser.paintLaser(graphics);


        astronaut1.paintAstronaut(graphics);
        astronaut2.paintAstronaut(graphics);

    }


    public int getBackgroundWidth() {
        return spaceBackgroundOne.getIconWidth();
    }


    public void gameScene() {

        this.sceneSound.startBackgroundPlay();
        this.sceneSound.loopPlay();


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
            astronaut1.leftRightMove(-1);
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
            astronaut2.leftRightMove(-1);
        }


        if (this.laser1.getX() == this.astronaut1.getX() + 20 && enterPressed) {
            this.laserShot1.startPlay();
        }
        if (this.laser2.getX() == this.astronaut2.getX() + 20 && spacePressed) {
            this.laserShot2.startPlay();
        }

        repaint();

        alienSpaceshipLoop();
        checkAliensCollision();
        keepLaser();
        moveLaser();
    }


    public void gameOver() {
        if (isSuccess) {
            this.setVisible(false);
            this.sceneSound.stopPlay();
            this.finalPanel.setVisible(true);
            this.finalPanel.start();
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.finalVideoMusic.playSound("src/main/java/resources/FinalGifAudio.wav");
            this.finalVideoMusic.startPlay();
            try {
                Thread.sleep(22200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.finalPanel.stop();
            this.finalPanel.showLobbyButton();
        } else if (isFailed) {
            showFailedDialog();
        }
    }


    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN -> downPressed = true;
            case KeyEvent.VK_UP -> upPressed = true;
            case KeyEvent.VK_RIGHT -> {
                rightPressed = true;
                this.astronaut1.setWalkingToRight(true);
            }
            case KeyEvent.VK_LEFT -> {
                leftPressed = true;
                this.astronaut1.setWalkingToRight(false);
            }
            case KeyEvent.VK_W -> wPressed = true;
            case KeyEvent.VK_S -> sPressed = true;
            case KeyEvent.VK_D -> {
                dPressed = true;
                this.astronaut2.setWalkingToRight(true);
            }
            case KeyEvent.VK_A -> {
                aPressed = true;
                this.astronaut2.setWalkingToRight(false);
            }
            case KeyEvent.VK_SPACE -> {
//                laserShot2.startPlay();
                if (!laser2Move) { // ירי ראשוני בלבד
                    isRightShoot2 = astronaut2.isWalkingToRight();
                }
                spacePressed = true;
                laser2Move = true;
            }
            case KeyEvent.VK_ENTER -> {
//                laserShot1.startPlay();
                if (!laser1Move) { // ירי ראשוני בלבד
                    isRightShoot1 = astronaut1.isWalkingToRight();
                }
                enterPressed = true;
                laser1Move = true;
            }
        }
    }


    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN -> downPressed = false;
            case KeyEvent.VK_UP -> upPressed = false;
            case KeyEvent.VK_RIGHT -> {
                rightPressed = false;
                if (leftPressed) {
                    astronaut1.setWalkingToRight(false);
                }
            }
            case KeyEvent.VK_LEFT -> {
                leftPressed = false;
                if (rightPressed) {
                    astronaut1.setWalkingToRight(true);
                }
            }
            case KeyEvent.VK_W -> wPressed = false;
            case KeyEvent.VK_S -> sPressed = false;
            case KeyEvent.VK_D -> {
                dPressed = false;
                if (aPressed) {
                    astronaut2.setWalkingToRight(false);
                }
            }
            case KeyEvent.VK_A -> {
                aPressed = false;
                if (dPressed) {
                    astronaut2.setWalkingToRight(true);
                }
            }
            case KeyEvent.VK_SPACE -> {
                spacePressed = false;

//                this.isRightShoot2 = astronaut2.isWalkingToRight();

            }

            case KeyEvent.VK_ENTER -> {
                enterPressed = false;
//                this.isRightShoot1 = astronaut1.isWalkingToRight();
            }
        }
    }

    public void keyTyped(KeyEvent e) {

    }


    public void alienSpaceshipLoop() {
        if (alienSpaceship.getX() < -alienSpaceship.getWidth()) {
            alienSpaceship.setRandomX(this.windowWidth, this.windowWidth + 600);
            alienSpaceship.setRandomY(0, this.windowHeight / 2 - this.alienSpaceship.getHeight() - 30);
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

        for (int i = 0; i < aliens.size(); i++) {
            if (this.aliens.get(i).rectangle().intersects(this.astronaut1.rectangle()) || this.aliens.get(i).rectangle().intersects(this.astronaut2.rectangle())) {
                counterOfMisses++;
                alienHasCollision = true;
                alienIndex = (byte) i;

                System.out.println("misses: " + counterOfMisses);
            } else if (this.aliens.get(i).rectangle().intersects(this.laser1.rectangle())) {
                alienHasCollision = true;
                alienIndex = (byte) i;
                laser1Colision = true;
                counterOfAlienHits++;
                System.out.println("hits: " + counterOfAlienHits);


            } else if (this.aliens.get(i).rectangle().intersects(this.laser2.rectangle())) {
                alienHasCollision = true;
                alienIndex = (byte) i;
                laser2Colision = true;
                counterOfAlienHits++;
                System.out.println("hits: " + counterOfAlienHits);


            } else if (this.astronaut1.rectangle().intersects(this.alienLaser.rectangle())) {
                hasAstronautHit = true;
                astronaut1HasColision = true;
                counterOfMisses++;
                System.out.println("misses: " + counterOfMisses);


            } else if (this.astronaut2.rectangle().intersects(this.alienLaser.rectangle())) {
                hasAstronautHit = true;
                astronaut2HasColision = true;
                counterOfMisses++;
                System.out.println("misses: " + counterOfMisses);

            }

        }

        if (alienHasCollision) {
            aliens.get(alienIndex).setWalkingRight(!aliens.get(alienIndex).isWalkingRight());
            aliens.get(alienIndex).setRandomX();
            alienHasCollision = false;

        }

        if (laser1Colision) {
            resetLaser1();
            laser1Colision = false;
        }

        if (laser2Colision) {
            resetLaser2();
            laser2Colision = false;
        }

        if (hasAstronautHit) {
            resetAlienLaser();
            hasAstronautHit = false;
        }

        if (counterOfMisses >= 1) {
            super.gameCondition = false;
            this.isFailed = true;
        }

        if (counterOfAlienHits >= 25) {
            super.gameCondition = false;
            this.isSuccess = true;
        }
    }


    public void keepLaser() {
        if (!laser1Move) {
            this.laser1X = astronaut1.getX() + 20;
            this.laser1Y = astronaut1.getY() + 37;
            this.laser1.setX(laser1X);
            this.laser1.setY(laser1Y);

        }
        if (!laser2Move) {
            this.laser2X = this.astronaut2.getX() + 20;
            this.laser2Y = this.astronaut2.getY() + 37;
            this.laser2.setX(laser2X);
            this.laser2.setY(laser2Y);
        }

        if (!laserAlienMove) {
            this.laserAlienX = this.alienSpaceship.getX() + 20;
            this.laserAlienY = this.alienSpaceship.getY() + 30;
            this.alienLaser.setX(laserAlienX);
            this.alienLaser.setY(laserAlienY);

        }


    }

    public void moveLaser() {
//        boolean isRightShoot1 = astronaut1.isWalkingToRight();
        if (laser1Move) {
            laser1.fire(isRightShoot1, 8);
            if (laser1.getX() > windowWidth || laser1.getX() + laser1.getWidth() < 0) {
                resetLaser1();
            }
        }

//        boolean isRightShoot2 = astronaut2.isWalkingToRight();
        if (laser2Move) {
            laser2.fire(isRightShoot2, 8);
            if (laser2.getX() > windowWidth || laser2.getX() + laser2.getWidth() < 0) {
                resetLaser2();
            }
        }

        if (alienLaser.getX() >= alienSpaceship.getFirePoint() - 3 && alienLaser.getX() <= alienSpaceship.getFirePoint() + 3) {
            laserAlienMove = true;
            alienLaser.fireDown();
            if (alienLaser.getY() > windowHeight) {
                resetAlienLaser();
            }
        }
    }

    public void resetLaser1() {

        laser1.setX(laser1X);
        laser1.setY(laser1Y);
        laser1Move = false;
    }

    public void resetLaser2() {
        laser2.setX(laser2X);
        laser2.setY(laser2Y);
        laser2Move = false;
    }

    public void resetAlienLaser() {
        alienSpaceship.randomFirePoint(50, windowWidth - 100);
        alienLaser.setX(laserAlienX);
        alienLaser.setY(laserAlienY);
        laserAlienMove = false;
//        this.firePoint = alienSpaceship1.getFirePoint();
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean isFailed() {
        return isFailed;
    }

//    public GifPanel getGifPanel() {
//        return gifPanel;
//    }

    public FinalPanel getFinalPanel() {
        return finalPanel;
    }

    public Sound getSceneSound() {
        return sceneSound;
    }

//    public JButton getLobbyButton(){
//        return finalPanel.getLobbyButton();
//    }
}
