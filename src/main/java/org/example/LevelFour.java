package org.example;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class LevelFour extends AbstractLevel implements KeyListener {
    private ImageIcon spaceshipBackground;
    private Astronaut astronaut;
    private int xOfBackground = 0;
    private boolean rightPressed = false;
    private boolean leftPressed = false;
    private int fakeButtonWidth = 200;
    private int xOfAstronaut;
    private final JButton circuitButton;
    private final JButton fakeButton;
    private int xOfCircuit;
    private int yOfCircuit;
    private static int counterOfLevel = 1;

    private Object[] options;
    private CircuitBreakerOne circuitBreaker1;
    private int selectedOption;

    private List<OptionSelectionListener> listeners = new ArrayList<>(); // רשימת מאזינים



    public LevelFour(int width, int height) {
        super.windowWidth = width;
        super.windowHeight = height;

        if (counterOfLevel == 1){
            circuitBreaker1 = new CircuitBreakerOne("src/main/java/resources/CircuitBreaker1.png");
        } else if (counterOfLevel == 2) {
            circuitBreaker1 = new CircuitBreakerOne("src/main/java/resources/CircuitBreaker2.png");
            circuitBreaker1.setxOfMoving1(circuitBreaker1.getPanelWidth() - 50);
            circuitBreaker1.setxOfMoving2(circuitBreaker1.getPanelWidth() - 50);

            circuitBreaker1.setyOfMoving1(100);
            circuitBreaker1.setyOfMoving2(125);

//            circuitBreaker1.setyOfMoving1(circuitBreaker1.getPanelHeight() - 100);
//            circuitBreaker1.setyOfMoving2(circuitBreaker1.getPanelHeight() - 75);

        }
        this.circuitBreaker1.setVisible(false);
        this.xOfCircuit = (this.windowWidth - this.circuitBreaker1.getPanelWidth()) / 2;
        this.yOfCircuit = (this.windowHeight - this.circuitBreaker1.getPanelHeight()) / 2;
        this.circuitBreaker1.setBounds(this.xOfCircuit, this.yOfCircuit, this.circuitBreaker1.getWidth(), this.circuitBreaker1.getHeight());
        this.add(circuitBreaker1);

        this.astronaut = new Astronaut();
        this.astronaut.setY(((windowHeight - this.astronaut.getHeight()) / 2) + 60);
        this.xOfAstronaut = (int) ((windowWidth - this.astronaut.getWidth()) / 2);
        this.astronaut.setX(xOfAstronaut);


        this.spaceBackgroundOne = null;
        this.spaceBackgroundTwo = null;
        this.spaceshipBackground = new ImageIcon("src/main/java/resources/levelTwoBackground.png");
        this.xOfBackground = -(this.spaceshipBackground.getIconWidth() - this.windowWidth) / 2;


        this.options = new Object[]{"Lobby", ""};


        this.fakeButton = new JButton("?");
        this.fakeButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.fakeButton.setContentAreaFilled(false);
        this.fakeButton.setBorderPainted(false);
        this.fakeButton.setFocusPainted(false);
        this.fakeButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.fakeButton.setFocusable(false);
        this.fakeButton.setForeground(new Color(142, 109, 4, 224));


        this.fakeButton.addActionListener(e -> {
            System.out.println("hahahaha you clicked on: fakeRiddleButton");

        });
        this.add(fakeButton);


        this.circuitButton = new JButton("click here");
        this.circuitButton.setFont(new Font("Arial", Font.BOLD, 30));
        this.circuitButton.setContentAreaFilled(false);
        this.circuitButton.setBorderPainted(false);
        this.circuitButton.setFocusPainted(false);
        this.circuitButton.setFocusable(false);
        this.circuitButton.setForeground(new Color(60, 124, 144, 255));
        this.circuitButton.addActionListener(e -> {
            System.out.println("you clicked on: showRiddleButton");


            this.circuitBreaker1.setVisible(true);
            this.circuitBreaker1.setFocusable(true);
            this.circuitBreaker1.requestFocus();
            this.circuitBreaker1.requestFocusInWindow();
        });
        this.add(circuitButton);





        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
        this.requestFocusInWindow();
        mainGameLoop();

    }


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (spaceshipBackground != null) {
            this.spaceshipBackground.paintIcon(null, graphics, xOfBackground, 0);
            this.fakeButton.setBounds(xOfBackground + 1320,140,28,45);
            this.circuitButton.setBounds(xOfBackground + (spaceshipBackground.getIconWidth() - this.fakeButtonWidth) / 2,260,this.fakeButtonWidth,45);
        }
        astronaut.paintAstronaut(graphics);

//        circuitBreaker1.paintCircuit1(graphics);
    }



    @Override
    public void gameScene() {
        if (rightPressed && (this.xOfBackground + this.getBackgroundWidth()) > this.windowWidth && !(xOfAstronaut > this.astronaut.getX()) ) {
            takeBackgroundLeft();
        } else if (rightPressed && (this.astronaut.getX() + this.astronaut.getWidth()) < this.windowWidth) {
            this.astronaut.leftRightMove(1);
        }
        if (leftPressed && this.xOfBackground < 0 && !(xOfAstronaut < this.astronaut.getX())) {
            takeBackgroundRight();
        }else if (leftPressed && this.astronaut.getX() > 0) {
            this.astronaut.leftRightMove(-1);
        }

        super.gameCondition = circuitBreaker1.isGameCondition();


        repaint();
    }

    @Override
    public void gameOver() {

        if (circuitBreaker1.isSuccess()){
            counterOfLevel++;
            showSuccessDialog();
        } else if (circuitBreaker1.isFailed()) {
            showFailedDialog();
        }

        circuitBreaker1.setVisible(false);
        remove(circuitBreaker1);

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT -> {
                this.rightPressed = true;
                this.astronaut.setMirrorChosen(false);
            }
            case KeyEvent.VK_LEFT -> {
                this.leftPressed = true;
                this.astronaut.setMirrorChosen(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT -> this.rightPressed = false;
            case KeyEvent.VK_LEFT -> this.leftPressed = false;
        }
    }

    @Override
    public int getBackgroundWidth() {
        return spaceshipBackground.getIconWidth();
    }


    public void takeBackgroundRight() {
        this.xOfBackground += 1;
    }
    public void takeBackgroundLeft() {
        this.xOfBackground -= 1;
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



    public boolean isSuccess() {
        return circuitBreaker1.isSuccess();
    }

    public boolean isFailed() {
        return circuitBreaker1.isFailed();
    }

    public JButton getCircuitButton() {
        return circuitButton;
    }


    public int getLevelCounter() {
        return counterOfLevel;
    }
}
