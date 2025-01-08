package org.example;

import db.JDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class LevelOne extends AbstractLevel implements KeyListener {
    private Spaceship spaceship1;
    private Spaceship spaceship2;
    private Fuel fuel;
    private Stone stone1;
    private Stone stone2;
    private Stone stone3;
    private Stone stone4;
    private Stone stone5;
    private Stone stone6;
    private Stone stone7;
    private Stone stone8;
    private boolean downPressed = false;
    private boolean upPressed = false;
    private boolean rightPressed = false;
    private boolean leftPressed = false;
    private boolean wPressed = false;
    private boolean sPressed = false;
    private boolean aPressed = false;
    private boolean dPressed = false;
    private List<Stone> stones;
    private boolean stoneHasCollision = false;
    private byte stoneIndex;
    private int counterOfStoneHits = 0;
    private boolean fuelHasCollision = false;
    private int counterOfFuelHits = 0;
    private Sound sceneSound;
    private Sound fuelSound;
    private Sound crashing;
    private Sound explosion;
    private Sound passedLevel;
    private Sound missionComplete;
    private ImageIcon finalMoonImage;
    private int finalMoonX = windowWidth;
    private Object[] options;
    private int selectedOption;
    private boolean isSuccess = counterOfFuelHits >= 5;
    private boolean isFailed = counterOfStoneHits >= 3;
    private ToolsOfLife toolsOfLife;
    private String teamName;


    private List<OptionSelectionListener> listeners = new ArrayList<>(); // רשימת מאזינים


    public LevelOne(int width, int height, String teamName) {
        this.teamName = teamName;

        // אתחול הרקעים קודם לקריאה לבנאי של המחלקה האבסטרקטית
        this.spaceBackgroundOne = new ImageIcon("src/main/java/resources/LevelOne.png");
        this.spaceBackgroundTwo = new ImageIcon("src/main/java/resources/LevelOneMirror.png");
        this.finalMoonImage = new ImageIcon("src/main/java/resources/final_moon.png");

        // קריאה לבנאי של המחלקה האבסטרקטית אחרי אתחול הרקעים
        super.windowWidth = width;
        super.windowHeight = height;
        super.xOfBackgroundTwo = this.spaceBackgroundOne.getIconWidth();

        super.levelInstructions = new LevelInstructions(super.windowWidth, super.windowHeight, "Level 1", " ", "src/main/java/resources/level1instructions.png", 100, windowHeight - 100);


        this.options = new Object[]{"Lobby", ""};

        this.spaceship1 = new Spaceship("src/main/java/resources/Spaceship1.png");
        this.spaceship1.setY(200);
        this.spaceship2 = new Spaceship("src/main/java/resources/Spaceship2.png");
        this.spaceship2.setY(500);

        this.fuel = new Fuel();
        this.fuel.setRandomX(this.windowWidth, this.windowWidth * 2);
        this.fuel.setRandomY(0, this.windowHeight - this.fuel.getHeight());
        this.fuel.start();

        this.stone1 = new Stone("src/main/java/resources/stone1.png");
        this.stone1.setRandomX(this.windowWidth, this.windowWidth * 2);
        this.stone1.setRandomY(0, this.windowHeight - this.stone1.getHeight());
        this.stone1.start();

        this.stone2 = new Stone("src/main/java/resources/stone2.png");
        this.stone2.setRandomX(this.windowWidth, this.windowWidth * 2);
        this.stone2.setRandomY(0, this.windowHeight - this.stone2.getHeight());
        this.stone2.start();

        this.stone3 = new Stone("src/main/java/resources/stone3.png");
        this.stone3.setRandomX(this.windowWidth, this.windowWidth * 2);
        this.stone3.setRandomY(0, this.windowHeight - this.stone3.getHeight());
        this.stone3.start();


        this.stone4 = new Stone("src/main/java/resources/stone1.png");
        this.stone4.setRandomX(this.windowWidth, this.windowWidth * 2);
        this.stone4.setRandomY(0, this.windowHeight - this.stone4.getHeight());
        this.stone4.start();

        this.stone5 = new Stone("src/main/java/resources/stone2.png");
        this.stone5.setRandomX(this.windowWidth, this.windowWidth * 2);
        this.stone5.setRandomY(0, this.windowHeight - this.stone5.getHeight());
        this.stone5.start();

        this.stone6 = new Stone("src/main/java/resources/stone3.png");
        this.stone6.setRandomX(this.windowWidth, this.windowWidth * 2);
        this.stone6.setRandomY(0, this.windowHeight - this.stone6.getHeight());
        this.stone6.start();

        this.stone7 = new Stone("src/main/java/resources/stone1.png");
        this.stone7.setRandomX(this.windowWidth, this.windowWidth * 2);
        this.stone7.setRandomY(0, this.windowHeight - this.stone7.getHeight());
        this.stone7.start();

        this.stone8 = new Stone("src/main/java/resources/stone2.png");
        this.stone8.setRandomX(this.windowWidth, this.windowWidth * 2);
        this.stone8.setRandomY(0, this.windowHeight - this.stone8.getHeight());
        this.stone8.start();


        stones = new ArrayList<>();
        stones.add(stone1);
        stones.add(stone2);
        stones.add(stone3);
        stones.add(stone4);
        stones.add(stone5);
        stones.add(stone6);
        stones.add(stone7);
        stones.add(stone8);


        this.sceneSound = new Sound();
        this.sceneSound.playSound("src/main/java/resources/space_background.wav");

        this.fuelSound = new Sound();
        this.fuelSound.playSound("src/main/java/resources/catching_fuel.wav");

        this.crashing = new Sound();
        this.crashing.playSound("src/main/java/resources/crashing.wav");

        this.explosion = new Sound();
        this.explosion.playSound("src/main/java/resources/explosion.wav");

        this.passedLevel = new Sound();
        this.passedLevel.playSound("src/main/java/resources/passed_level.wav");

        this.missionComplete = new Sound();
        this.missionComplete.playSound("src/main/java/resources/mission_completed.wav");

        this.toolsOfLife = new ToolsOfLife();


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

//        System.out.println(isSuccess);
        this.isSuccess = counterOfFuelHits >= 5;
        this.isFailed = counterOfStoneHits >= 3;
        super.gameCondition = !isFailed && !isSuccess;


        // חללית 1 - תנועה אנכית ואופקית
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


        hideTools();
        stonesLoop();   //חזרה של האבנים
        checkCollision();  // בדיקת פגיעה בין האבנים לחלליות
        fuelLoop(); //בדיקת פגיעה בין הדלק לחלליות
    }

    @Override
    public void gameOver() {
        if (isSuccess) {
            while (finalMoonX > 0) {
                repaint();
                finalMoonX -= 2;
                try {
                    Thread.sleep(7);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            passedLevel.startPlay();
            missionComplete.startPlay();
            JDBC.updateLevel(this.teamName, 2);
            showSuccessDialog();
        } else if (isFailed) {
            showFailedDialog();
        }

    }


    public void showSuccessDialog() {
//        this.options[0] = "Lobby";
//        this.options = new Object[]{"Lobby", ""};
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
        spaceship1.paintSpaceship(graphics);
        spaceship2.paintSpaceship(graphics);
        fuel.paintFuel(graphics);
        stone1.paintStone(graphics);
        stone2.paintStone(graphics);
        stone3.paintStone(graphics);
        stone4.paintStone(graphics);
        stone5.paintStone(graphics);
        stone6.paintStone(graphics);
        stone7.paintStone(graphics);
        stone8.paintStone(graphics);
        toolsOfLife.paintTools(graphics);

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
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    public void stonesLoop() {
        for (int i = 0; i < stones.size(); i++) {
            if (stones.get(i).getX() < -stones.get(i).getWidth()) {
                stones.get(i).setRandomX(this.windowWidth, this.windowWidth + 600);
                stones.get(i).setRandomY(0, this.windowHeight - this.stones.get(i).getHeight());
            }
        }
    }

    public void fuelLoop() {
        if (fuel.getX() < -fuel.getWidth()) {
            this.fuel.setRandomX(this.windowWidth, this.windowWidth * 2);
            this.fuel.setRandomY(0, this.windowHeight - this.fuel.getHeight());

        }
    }


    public void checkCollision() {
        if (this.fuel.rectangle().intersects(this.spaceship1.rectangle()) || this.fuel.rectangle().intersects(this.spaceship2.rectangle())) {
            fuelSound.startPlay();
            System.out.println(" fuel crash");
            fuelHasCollision = true;
            counterOfFuelHits++;
        }
        for (int i = 0; i < stones.size(); i++) {
            if (this.stones.get(i).rectangle().intersects(this.spaceship1.rectangle()) || this.stones.get(i).rectangle().intersects(this.spaceship2.rectangle())) {
                crashing.startPlay();
                counterOfStoneHits++;
                stoneHasCollision = true;
                stoneIndex = (byte) i;
                if (counterOfStoneHits == 3) {
                    explosion.startPlay();
//                    System.out.println("game over - lose");
                }
            }
        }
        if (stoneHasCollision) {
            stones.get(stoneIndex).setRandomX(this.windowWidth, this.windowWidth + 600);
            stones.get(stoneIndex).setRandomY(0, this.windowHeight - this.stone2.getHeight());
            stoneHasCollision = false;

        }
        if (fuelHasCollision) {
            this.fuel.setRandomX(this.windowWidth, this.windowWidth * 2);
            this.fuel.setRandomY(0, this.windowHeight - this.fuel.getHeight());
            System.out.println("Fuel hits: " + counterOfFuelHits);
            fuelHasCollision = false;
        }
    }

    public Sound getSceneSound() {
        return sceneSound;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean isFailed() {
        return isFailed;
    }

    public void hideTools() {

        if (counterOfStoneHits >= 1) {
            toolsOfLife.hideHeart3();
        }
        if (counterOfStoneHits >= 2) {
            toolsOfLife.hideHeart2();
        }
        if (counterOfStoneHits >= 3) {
            toolsOfLife.hideHeart1();
        }
    }
}
