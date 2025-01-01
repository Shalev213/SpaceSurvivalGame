package org.example;

import db.JDBC;
import opencv.OpenCVProcessor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class LevelThree extends AbstractLevel implements KeyListener {
    private Spaceship spaceship1;
    private Spaceship spaceship2;
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
    private boolean laser1Move = false;
    private boolean laser2Move = false;
    private List<AlienSpaceship> alienSpaceships;
    private boolean alienHasCollision = false;
    private boolean laser1Colision = false;
    private boolean laser2Colision = false;
    private byte alienIndex;
    private int counterOfMisses = 0;
    private boolean hasMisses = false;
    private int counterOfAlienHits = 0;
    private Sound sceneSound;
    private Sound laserShot1;
    private Sound laserShot2;
    private Sound crashing;
    private Sound explosion;
    private Sound passedLevel;
    private Sound missionComplete;
    private ImageIcon finalMoonImage;
    private int finalMoonX = windowWidth;
    private Object[] options;
    private int selectedOption;
    private boolean isSuccess = counterOfAlienHits >= 15;
    private boolean isFailed = counterOfMisses >= 3;
    private AlienSpaceship alienSpaceship1;
    private AlienSpaceship alienSpaceship2;
    private AlienSpaceship alienSpaceship3;
    private Laser laser1;
    private Laser laser2;
    private int laser1X = 0;
    private int laser1Y = 0;
    private int laser2X = 0;
    private int laser2Y = 0;
    private String teamName;
    private List<OptionSelectionListener> listeners = new ArrayList<>(); // רשימת מאזינים
    private Robot robot;


    public LevelThree(int width, int height, String teamName) {
        setDoubleBuffered(true);
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        this.teamName = teamName;
        this.spaceBackgroundOne = new ImageIcon("src/main/java/resources/LevelThreeBackground.png");
        this.spaceBackgroundTwo = new ImageIcon("src/main/java/resources/LevelThreeMirror.png");
        this.finalMoonImage = new ImageIcon("src/main/java/resources/final_moon.png");


        super.windowWidth = width;
        super.windowHeight = height;
        super.xOfBackgroundTwo = this.spaceBackgroundOne.getIconWidth();

        this.options = new Object[]{"Lobby", ""};


        this.spaceship1 = new Spaceship("src/main/java/resources/Spaceship1.png");
        this.spaceship1.setY(200);
        this.spaceship2 = new Spaceship("src/main/java/resources/Spaceship2.png");
        this.spaceship2.setY(500);

        this.laser1X = this.spaceship1.getX() + 65;
        this.laser1Y = this.spaceship1.getY() + 30;

        this.laser2X = this.spaceship2.getX() + 65;
        this.laser2Y = this.spaceship2.getY() + 30;


        this.alienSpaceship1 = new AlienSpaceship("src/main/java/resources/AlienSpaceship1.png");
        this.alienSpaceship1.setRandomX(this.windowWidth, this.windowWidth * 2);
        this.alienSpaceship1.setRandomY(0, this.windowHeight - this.alienSpaceship1.getHeight() - 30);
        this.alienSpaceship1.start();

        this.alienSpaceship2 = new AlienSpaceship("src/main/java/resources/AlienSpaceship2.png");
        this.alienSpaceship2.setRandomX(this.windowWidth, this.windowWidth * 2);
        this.alienSpaceship2.setRandomY(0, this.windowHeight - this.alienSpaceship2.getHeight() - 30);
        this.alienSpaceship2.start();

        this.alienSpaceship3 = new AlienSpaceship("src/main/java/resources/AlienSpaceship1.png");
        this.alienSpaceship3.setRandomX(this.windowWidth, this.windowWidth * 2);
        this.alienSpaceship3.setRandomY(0, this.windowHeight - this.alienSpaceship3.getHeight() - 30);
        this.alienSpaceship3.start();


        alienSpaceships = new ArrayList<>();
        alienSpaceships.add(alienSpaceship1);
        alienSpaceships.add(alienSpaceship2);
        alienSpaceships.add(alienSpaceship3);


        this.laser1 = new Laser("src/main/java/resources/YellowLaser.png");
        this.laser1.setX(laser1X);
        this.laser1.setY(laser1Y);


        this.laser2 = new Laser("src/main/java/resources/YellowLaser.png");
        this.laser2.setX(laser2X);
        this.laser2.setY(laser2Y);


        this.sceneSound = new Sound();
        this.sceneSound.playSound("src/main/java/resources/space_background.wav");

        this.laserShot1 = new Sound();
        this.laserShot1.playSound("src/main/java/resources/laser-sound.wav");

        this.laserShot2 = new Sound();
        this.laserShot2.playSound("src/main/java/resources/laser-sound.wav");

        this.crashing = new Sound();
        this.crashing.playSound("src/main/java/resources/crashing.wav");

        this.explosion = new Sound();
        this.explosion.playSound("src/main/java/resources/explosion.wav");

        this.passedLevel = new Sound();
        this.passedLevel.playSound("src/main/java/resources/passed_level.wav");

        this.missionComplete = new Sound();
        this.missionComplete.playSound("src/main/java/resources/mission_completed.wav");


        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
        this.requestFocusInWindow();
        mainGameLoop();
    }

    @Override
    public int getBackgroundWidth() {
        return spaceBackgroundOne.getIconWidth();
    }


    @Override
    public void gameScene() {
        backgroundLoop();
        this.sceneSound.startBackgroundPlay();
        this.sceneSound.loopPlay();

        this.isSuccess = counterOfAlienHits >= 15;
        this.isFailed = counterOfMisses >= 3;
        this.gameCondition = !isFailed && !isSuccess;


        if (downPressed && this.spaceship1.getY() <= (windowHeight - 1.5 * spaceship1.getHeight())) {
            spaceship1.upDownMove(1);
        }
        if (upPressed && this.spaceship1.getY() >= 0) {
            spaceship1.upDownMove(-1);
        }
        if (rightPressed && this.spaceship1.getX() <= (this.windowWidth - 1.2 * spaceship1.getWidth())) {
            spaceship1.leftRightMove(1);
        }
        if (leftPressed && this.spaceship1.getX() >= 0) {
            spaceship1.leftRightMove(-2);
        }

        // חללית 2 - תנועה אנכית ואופקית
        if (sPressed && this.spaceship2.getY() <= (windowHeight - 1.75 * spaceship2.getHeight())) {
            spaceship2.upDownMove(1);
        }
        if (wPressed && this.spaceship2.getY() >= 0) {
            spaceship2.upDownMove(-1);
        }
        if (dPressed && this.spaceship2.getX() <= (this.windowWidth - 1.2 * spaceship2.getWidth())) {
            spaceship2.leftRightMove(1);
        }
        if (aPressed && this.spaceship2.getX() >= 0) {
            spaceship2.leftRightMove(-2);
        }


        if (this.laser1.getX() == this.spaceship1.getX() + 65 && enterPressed) {
            this.laserShot1.startPlay();
        }
        if (this.laser2.getX() == this.spaceship2.getX() + 65 && spacePressed) {
            this.laserShot2.startPlay();
        }


        moveLaser();
        keepLaser();
        alienSpaceshipsLoop();
        checkCollision();
    }

    @Override
    public void gameOver() {
        if (isSuccess) {
            while (finalMoonX > 0) {
                repaint();
                finalMoonX -= 2;
                try {
                    Thread.sleep(6);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            passedLevel.startPlay();
            missionComplete.startPlay();
            JDBC.updateLevel(teamName, 4);
            showSuccessDialog();
        } else if (isFailed) {
            showFailedDialog();
        }

    }


    public void showSuccessDialog() {
        this.options[1] = "Next level";

        this.selectedOption = JOptionPane.showOptionDialog(null,
                "Mission Complete",
                null,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        // מפעיל את כל המאזינים עם התוצאה שנבחרה
        notifyListeners(selectedOption);
    }

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
    } // פעולה להוספת מאזין לרשימה

    public void addOptionSelectionListener(OptionSelectionListener listener) {
        listeners.add(listener);
    }

    // פעולה להפעיל את כל המאזינים כאשר אופציה נבחרת
    private void notifyListeners(int selectedOption) {
        for (OptionSelectionListener listener : listeners) {
            listener.onOptionSelected(selectedOption); // מפעיל את המאזין
        }
    }


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        finalMoonImage.paintIcon(null, graphics, finalMoonX, windowHeight - finalMoonImage.getIconHeight());
        laser1.paintLaser(graphics);
        laser2.paintLaser(graphics);
        spaceship1.paintSpaceship(graphics);
        spaceship2.paintSpaceship(graphics);
//        fuel.paintFuel(graphics);
        alienSpaceship1.paintAlienSpaceship(graphics);
        alienSpaceship2.paintAlienSpaceship(graphics);
        alienSpaceship3.paintAlienSpaceship(graphics);
//        alienSpaceship4.paintAlienSpaceship(graphics);
//        alienSpaceship5.paintAlienSpaceship(graphics);
        updatePlayerOne();
        updatePlayerTwo();

    }

    @Override
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

    @Override
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

    @Override
    public void keyTyped(KeyEvent e) {
    }


    public void alienSpaceshipsLoop() {
        for (int i = 0; i < alienSpaceships.size(); i++) {
            if (alienSpaceships.get(i).getX() < -alienSpaceships.get(i).getWidth()) {
                alienSpaceships.get(i).setRandomX(this.windowWidth, this.windowWidth + 600);
                alienSpaceships.get(i).setRandomY(0, this.windowHeight - this.alienSpaceships.get(i).getHeight() - 30);
            }
        }
    }


    public void checkCollision() {

        for (int i = 0; i < alienSpaceships.size(); i++) {
            if (this.alienSpaceships.get(i).rectangle().intersects(this.spaceship1.rectangle()) || this.alienSpaceships.get(i).rectangle().intersects(this.spaceship2.rectangle())) {
                crashing.startPlay();
                counterOfMisses++;
                System.out.println("Miss: " + counterOfMisses);
                alienHasCollision = true;
                alienIndex = (byte) i;
            } else if (this.alienSpaceships.get(i).rectangle().intersects(this.laser1.rectangle())) {
                alienHasCollision = true;
                alienIndex = (byte) i;
                laser1Colision = true;
                counterOfAlienHits++;
            } else if (this.alienSpaceships.get(i).rectangle().intersects(this.laser2.rectangle())) {
                alienHasCollision = true;
                alienIndex = (byte) i;
                laser2Colision = true;
                counterOfAlienHits++;
            } else if (this.alienSpaceships.get(i).getX() < 0) {
                hasMisses = true;
                alienIndex = (byte) i;
                counterOfMisses++;
//                System.out.println("Miss: " + counterOfMisses);
            }
        }

        if (alienHasCollision) {
            alienSpaceships.get(alienIndex).setRandomX(this.windowWidth, this.windowWidth + 600);
            alienSpaceships.get(alienIndex).setRandomY(0, this.windowHeight - this.alienSpaceship1.getHeight() - 30);
            alienHasCollision = false;
        }

        if (laser1Colision) {
            this.laser1.setX(laser1X);
            this.laser1.setY(laser1Y);

            laser1Move = false;
            laser1Colision = false;
        }

        if (laser2Colision) {
            this.laser2.setX(laser2X);
            this.laser2.setY(laser2Y);

            laser2Move = false;
            laser2Colision = false;
        }

        if (hasMisses) {
            alienSpaceships.get(alienIndex).setRandomX(this.windowWidth, this.windowWidth + 600);
            alienSpaceships.get(alienIndex).setRandomY(0, this.windowHeight - this.alienSpaceship1.getHeight());
            hasMisses = false;
        }

        if (counterOfMisses == 3) {
            explosion.startPlay();
        }
    }

    public void keepLaser() {
        if (!laser1Move) {
            this.laser1X = spaceship1.getX() + 65;
            this.laser1Y = spaceship1.getY() + 30;
            this.laser1.setX(laser1X);
            this.laser1.setY(laser1Y);

        }
        if (!laser2Move) {
            this.laser2X = this.spaceship2.getX() + 65;
            this.laser2Y = this.spaceship2.getY() + 30;
            this.laser2.setX(laser2X);
            this.laser2.setY(laser2Y);
        }
    }

    public void moveLaser() {
        if (laser1Move) {
            laser1.fire(true);
            if (laser1.getX() > windowWidth) {
                resetLaser1();
                laser1Move = false;
            }
        }

        if (laser2Move) {
            laser2.fire(true);
            if (laser2.getX() > windowWidth) {
                resetLaser2();
                laser2Move = false;
            }
        }
    }

    public void resetLaser1() {
        laser1.setX(laser1X);
        laser1.setY(laser1Y);
    }

    public void resetLaser2() {
        laser2.setX(laser2X);
        laser2.setY(laser2Y);
    }

    private void updatePlayerOne() {
        int greenPosition = OpenCVProcessor.getMarkerPosition("green", true);

        switch (greenPosition) {
            case 0 -> {
                robot.keyPress(KeyEvent.VK_UP);
                robot.keyRelease(KeyEvent.VK_DOWN);
            }
            case 2 -> {
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_UP);
            }
        }
    }

    private void updatePlayerTwo() { // שחקן שמאלי (צהוב במקום כתום)
        int yellowPosition = OpenCVProcessor.getMarkerPosition("yellow", false);

        switch (yellowPosition) {
            case 0 -> {
                robot.keyPress(KeyEvent.VK_W);
                robot.keyRelease(KeyEvent.VK_S);
            }
            case 2 -> {
                robot.keyPress(KeyEvent.VK_S);
                robot.keyRelease(KeyEvent.VK_W);
            }
        }
    }


    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean isFailed() {
        return isFailed;
    }

    public Sound getSceneSound() {
        return sceneSound;
    }
}
