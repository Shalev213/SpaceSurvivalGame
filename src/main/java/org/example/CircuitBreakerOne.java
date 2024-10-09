package org.example;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CircuitBreakerOne extends JPanel implements KeyListener {
    private final int panelWidth;
    private final int panelHeight;
    private int x = 0;
    private int y = 0;
    private ImageIcon circuitBreaker1;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean rightPressed = false;
    private boolean leftPressed = false;
    private Point currentPoint;

    private List<Point> trail = new ArrayList<>();
    private int xOfMoving = 50, yOfMoving = 50; // מיקום התחלתי
    private int speed = 1; // מהירות תנועה
    private boolean isFailed = false;
    private boolean isSuccess = false;



    private BufferedImage backgroundImage; // תמונת הרקע שנקראת מהקובץ

    private boolean gameCondition = true;


    private int pixelColor;
    private Color currentColor;



//    private boolean isFailed = false;





    public CircuitBreakerOne() {
        this.circuitBreaker1 = new ImageIcon("src/main/java/resources/CircuitBreaker1.png");
        this.panelHeight = this.circuitBreaker1.getIconHeight();
        this.panelWidth = this.circuitBreaker1.getIconWidth();
        this.setSize(panelWidth, panelHeight);
        this.setLayout(null);
        this.setVisible(false);



        try {
            // טען את תמונת הרקע
            backgroundImage = ImageIO.read(new File("src/main/java/resources/CircuitBreaker1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }




        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
        this.requestFocusInWindow();

    }


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (circuitBreaker1 != null) {
            this.circuitBreaker1.paintIcon(null, graphics, 0, 0);
//            graphics.drawImage(this.circuitBreaker1, this.x, this.y, this.panelWidth, this.panelHeight, null);
        }

        graphics.setColor(Color.GREEN);
        for (Point p : trail) {
            graphics.fillRect(p.x, p.y, 10, 10);

        }
        if(upPressed) {
            yOfMoving -= speed;
        } else if (downPressed) {
            yOfMoving += speed;
        } else if (leftPressed) {
            xOfMoving -= speed;
        } else if (rightPressed) {
            xOfMoving += speed;
        }



        // שמור את המיקום הנוכחי לשובל
        trail.add(new Point(xOfMoving, yOfMoving));
        this.currentPoint = trail.getLast();


        if (checkCollision() && !isFailed) {
            isFailed = true;
            gameCondition = false;
//            JOptionPane.showMessageDialog(null, "פגעת בקיר!");

        }



//        repaint();

//        // צייר את האובייקט עצמו
//        graphics.setColor(Color.RED);
//        graphics.fillRect(x, y, 10, 10);

    }



//    public void mainGameLoop() {
//        new Thread(() -> {
//            while (gameCondition) {
////                backgroundLoop();
//                gameScene();
//                repaint();
//
//                try {
//                    Thread.sleep(7);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            gameOver();
//        }).start();
//    }
//
//
//    public void gameScene() {
//
//    }
//
//    public void gameOver() {
//
//    }



        @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN ->{
                downPressed = true;
                upPressed = false;
                rightPressed = false;
                leftPressed = false;
            }
            case KeyEvent.VK_UP -> {
                upPressed = true;
                downPressed = false;
                rightPressed = false;
                leftPressed = false;
            }
            case KeyEvent.VK_RIGHT -> {
                rightPressed = true;
                downPressed = false;
                upPressed = false;
                leftPressed = false;
            }
            case KeyEvent.VK_LEFT ->{
                leftPressed = true;
                downPressed = false;
                upPressed = false;
                rightPressed = false;
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }



    private boolean checkCollision() {

       if (xOfMoving > 0 && yOfMoving > 0 && xOfMoving < this.panelWidth && yOfMoving < this.panelHeight){  //תנאי שהבדיקה תתבצע בתוך תחומי התמונה בלבד ולא מחוצה לה, שאחרת יהיו שגיאות

           pixelColor = backgroundImage.getRGB(xOfMoving, yOfMoving);
           currentColor = new Color(pixelColor);

//        System.out.println(currentColor);

           int tolerance = 20;   // טווח קרוב לצבע השחור
           if (isColorCloseToBlack(currentColor, tolerance)) { // אם פגע במחיצה
               return true;
           }
       }

        if (xOfMoving < 0 || yOfMoving < 0 || xOfMoving > this.panelWidth || yOfMoving > this.panelHeight) {
            return true;
        }

        return false;
    }

    // פונקציה שעוזרת לבדוק אם הצבע קרוב לשחור
    private boolean isColorCloseToBlack(Color color, int tolerance) {
        return color.getRed() < tolerance && color.getGreen() < tolerance && color.getBlue() < tolerance;
    }



    public int getPanelWidth() {
        return panelWidth;
    }

    public int getPanelHeight() {
        return panelHeight;
    }


    public boolean isGameCondition() {
        return gameCondition;
    }


    public boolean isFailed() {
        return isFailed;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
