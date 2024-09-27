package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class LevelThree extends AbstractLevel implements KeyListener {
    private Spaceship spaceship1;
    private Spaceship spaceship2;
//    private Fuel fuel;
//    private Stone stone1;
//    private Stone stone2;
//    private Stone stone3;
//    private Stone stone4;
//    private Stone stone5;
//    private Stone stone6;
//    private Stone stone7;
//    private Stone stone8;
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
    private int counterOfAlienHits = 0;
    private boolean fuelHasCollision = false;
    private int counterOfFuelHits = 0;
    private Sound sceneSound;
    private Sound fuelSound;
    private Sound crashing;
    private Sound explosion;
    private Sound passedLevel;
    private Sound missionComplete;
    private ImageIcon finalBackground;
    private int finalBackgroundX = windowWidth;
    private Object[] options;
    private int selectedOption;
    private boolean isSuccess = counterOfFuelHits >= 5;
    private boolean isFailed = counterOfAlienHits >= 3;
    private AlienSpaceship alienSpaceship1;
    private AlienSpaceship alienSpaceship2;
    private AlienSpaceship alienSpaceship3;
    private AlienSpaceship alienSpaceship4;
    private AlienSpaceship alienSpaceship5;
    private Laser laser1;
    private Laser laser2;




    private List<OptionSelectionListener> listeners = new ArrayList<>(); // רשימת מאזינים





    public LevelThree(int width, int height, String teamName) {
        // אתחול הרקעים קודם לקריאה לבנאי של המחלקה האבסטרקטית
        this.spaceBackgroundOne = new ImageIcon("src/main/java/resources/LevelThreeBackground.png");
        this.spaceBackgroundTwo = new ImageIcon("src/main/java/resources/LevelThreeMirror.png");
        this.finalBackground = new ImageIcon("src/main/java/resources/final_moon.png");

        // קריאה לבנאי של המחלקה האבסטרקטית אחרי אתחול הרקעים
        super.windowWidth = width;
        super.windowHeight = height;
        super.xOfBackgroundTwo = this.spaceBackgroundOne.getIconWidth();

        this.options = new Object[]{"Lobby", ""};


        this.spaceship1 = new Spaceship("src/main/java/resources/Spaceship1.png");
        this.spaceship1.setY(200);
        this.spaceship2 = new Spaceship("src/main/java/resources/Spaceship2.png");
        this.spaceship2.setY(500);

//        this.fuel = new Fuel();
//        this.fuel.setRandomX(this.windowWidth, this.windowWidth * 2);
//        this.fuel.setRandomY(0, this.windowHeight - this.fuel.getHeight());
//        this.fuel.start();

        this.alienSpaceship1 = new AlienSpaceship("src/main/java/resources/AlienSpaceship1.png");
        this.alienSpaceship1.setRandomX(this.windowWidth, this.windowWidth * 2);
        this.alienSpaceship1.setRandomY(0, this.windowHeight - this.alienSpaceship1.getHeight());
        this.alienSpaceship1.start();

        this.alienSpaceship2 = new AlienSpaceship("src/main/java/resources/AlienSpaceship2.png");
        this.alienSpaceship2.setRandomX(this.windowWidth, this.windowWidth * 2);
        this.alienSpaceship2.setRandomY(0, this.windowHeight - this.alienSpaceship2.getHeight());
        this.alienSpaceship2.start();

        this.alienSpaceship3 = new AlienSpaceship("src/main/java/resources/AlienSpaceship1.png");
        this.alienSpaceship3.setRandomX(this.windowWidth, this.windowWidth * 2);
        this.alienSpaceship3.setRandomY(0, this.windowHeight - this.alienSpaceship3.getHeight());
        this.alienSpaceship3.start();


        this.alienSpaceship4 = new AlienSpaceship("src/main/java/resources/AlienSpaceship2.png");
        this.alienSpaceship4.setRandomX(this.windowWidth, this.windowWidth * 2);
        this.alienSpaceship4.setRandomY(0, this.windowHeight - this.alienSpaceship4.getHeight());
        this.alienSpaceship4.start();

        this.alienSpaceship5 = new AlienSpaceship("src/main/java/resources/AlienSpaceship1.png");
        this.alienSpaceship5.setRandomX(this.windowWidth, this.windowWidth * 2);
        this.alienSpaceship5.setRandomY(0, this.windowHeight - this.alienSpaceship5.getHeight());
        this.alienSpaceship5.start();


        alienSpaceships = new ArrayList<>();
        alienSpaceships.add(alienSpaceship1);
        alienSpaceships.add(alienSpaceship2);
        alienSpaceships.add(alienSpaceship3);
        alienSpaceships.add(alienSpaceship4);
        alienSpaceships.add(alienSpaceship5);


        this.laser1 = new Laser("src/main/java/resources/YellowLaser.png");
        this.laser1.setX(this.spaceship1.getWidth());
        this.laser1.setY(this.spaceship1.getHeight());

        this.laser2 = new Laser("src/main/java/resources/YellowLaser.png");
        this.laser2.setX(this.spaceship2.getWidth());
        this.laser2.setY(this.spaceship2.getHeight());


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
        this.isFailed = counterOfAlienHits >= 3;
        this.gameCondition = !isFailed && !isSuccess;


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

//        if (spacePressed && laser1Move) { // רק אם הלייזר לא בתנועה, אפשר לירות
//            laser1Move = true; // הלייזר מתחיל לנוע
//        }
//
//        if (enterPressed && laser2Move) { // רק אם הלייזר לא בתנועה, אפשר לירות
//            laser2Move = true; // הלייזר מתחיל לנוע
//        }




//        hideTools();
        moveLaser();
        keepLaser();// בדיקת פגיעה בין האבנים לחלליות
        alienSpaceshipsLoop();
        checkCollision();
//        fuelLoop(); //בדיקת פגיעה בין הדלק לחלליות
    }

    @Override
    public void gameOver() {
        if (isSuccess){
            while (finalBackgroundX > 0){
                repaint();
                finalBackgroundX -= 1;
                try {
                    Thread.sleep(7);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            passedLevel.startPlay();
            missionComplete.startPlay();
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

        finalBackground.paintIcon(null, graphics, finalBackgroundX, windowHeight - finalBackground.getIconHeight());
        spaceship1.paintSpaceship(graphics);
        spaceship2.paintSpaceship(graphics);
//        fuel.paintFuel(graphics);
        alienSpaceship1.paintAlienSpaceship(graphics);
        alienSpaceship2.paintAlienSpaceship(graphics);
        alienSpaceship3.paintAlienSpaceship(graphics);
        alienSpaceship4.paintAlienSpaceship(graphics);
        alienSpaceship5.paintAlienSpaceship(graphics);
        laser1.paintLaser(graphics);
        laser2.paintLaser(graphics);

//        stone6.paintStone(graphics);
//        stone7.paintStone(graphics);
//        stone8.paintStone(graphics);
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
                System.out.println("space");
                spacePressed = true;
                laser1Move = true;
            }
            case KeyEvent.VK_ENTER -> {
                System.out.println("enter");
                enterPressed = true;
                laser2Move = true;
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
                alienSpaceships.get(i).setRandomY(0, this.windowHeight - this.alienSpaceships.get(i).getHeight());
            }
        }
    }

//    public void fuelLoop() {
//        if (fuel.getX() < -fuel.getWidth()){
//            this.fuel.setRandomX(this.windowWidth, this.windowWidth * 2);
//            this.fuel.setRandomY(0, this.windowHeight - this.fuel.getHeight());
//
//        }
//    }


    public void checkCollision() {
//        if (this.fuel.rectangle().intersects(this.spaceship1.rectangle()) || this.fuel.rectangle().intersects(this.spaceship2.rectangle())){
//            fuelSound.startPlay();
//            System.out.println(" fuel crash");
//            fuelHasCollision = true;
//            counterOfFuelHits++;
//        }
        for (int i = 0; i < alienSpaceships.size(); i++) {
            if (this.alienSpaceships.get(i).rectangle().intersects(this.spaceship1.rectangle()) || this.alienSpaceships.get(i).rectangle().intersects(this.spaceship2.rectangle())) {
                crashing.startPlay();
                counterOfAlienHits++;
                alienHasCollision = true;
                alienIndex = (byte) i;
                if (counterOfAlienHits == 3){
                    explosion.startPlay();
                    System.out.println("game over - lose");
                }
            } else if (this.alienSpaceships.get(i).rectangle().intersects(this.laser1.rectangle())) {
                alienHasCollision = true;
                alienIndex = (byte) i;
                laser1Colision = true;
            } else if (this.alienSpaceships.get(i).rectangle().intersects(this.laser2.rectangle())) {
                alienHasCollision = true;
                alienIndex = (byte) i;
                laser2Colision = true;
            }
        }

        if (alienHasCollision){
            alienSpaceships.get(alienIndex).setRandomX(this.windowWidth, this.windowWidth + 600);
            alienSpaceships.get(alienIndex).setRandomY(0, this.windowHeight - this.alienSpaceship1.getHeight());
            alienHasCollision = false;

        }

        if (laser1Colision) {
            this.laser1.setX((short) (this.spaceship1.getX()));
            this.laser1.setY(this.spaceship1.getY());
            laser1Move = false; // הלייזר חוזר להיות מסונכרן עם החללית
            laser1Colision = false;
        }
        if (laser2Colision) {
            this.laser2.setX((short) (this.spaceship2.getX()));
            this.laser2.setY(this.spaceship2.getY());
            laser2Move = false; // הלייזר חוזר להיות מסונכרן עם החללית
            laser2Colision = false;
        }




//        if (fuelHasCollision){
//            this.fuel.setRandomX(this.windowWidth, this.windowWidth * 2);
//            this.fuel.setRandomY(0, this.windowHeight - this.fuel.getHeight());
//            System.out.println("Fuel hits: " + counterOfFuelHits);
//            fuelHasCollision = false;
//        }
    }

    public void keepLaser() {
        if (!laser1Move) {
            laser1.setX((short) (this.spaceship1.getX()));
            laser1.setY(this.spaceship1.getY());
        }
        if (!laser2Move) {
            laser2.setX((short) (this.spaceship2.getX()));
            laser2.setY(this.spaceship2.getY());
        }
    }

    public void moveLaser() {
        if (laser1Move) {
            laser1.fire();
            if (laser1.getX() > windowWidth) {
                resetLaser1(); // מחזיר את הלייזר לחללית
                laser1Move = false; // הלייזר יצא מהמסך, מפסיק תנועה
            }
        }

        if (laser2Move) {
            laser2.fire();
            if (laser2.getX() > windowWidth) {
                resetLaser2(); // מחזיר את הלייזר לחללית
                laser2Move = false; // הלייזר יצא מהמסך, מפסיק תנועה
            }
        }
    }

    public void resetLaser1() {
        laser1.setX((short) (spaceship1.getX()));
        laser1.setY(spaceship1.getY());
//        laser1Move = false; // מחזירים את הלייזר למקום ומפסיקים את התנועה
    }

    public void resetLaser2() {
        laser2.setX((short) (spaceship2.getX()));
        laser2.setY(spaceship2.getY());
//        laser2Move = false; // מחזירים את הלייזר למקום ומפסיקים את התנועה
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
