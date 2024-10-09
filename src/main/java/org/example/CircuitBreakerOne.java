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
    private int panelWidth ;
    private int panelHeight ;
    private final int x = 0;
    private final int y = 0;
    private ImageIcon circuitBreaker1;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean rightPressed = false;
    private boolean leftPressed = false;

    private boolean sPressed = false;
    private boolean wPressed = false;
    private boolean dPressed = false;
    private boolean aPressed = false;





    private Point currentPoint;

    private List<Point> trail1 = new ArrayList<>();
    private List<Point> trail2 = new ArrayList<>();

    private int xOfMoving1 = 50, yOfMoving1 = 50; // מיקום התחלתי
    private int xOfMoving2 = 50, yOfMoving2 = 75; // מיקום התחלתי

    private int speed = 1; // מהירות תנועה
    private boolean isFailed = false;
    private boolean isSuccess = false;



    private BufferedImage backgroundImage; // תמונת הרקע שנקראת מהקובץ

    private boolean gameCondition = true;


    private int pixelColor1;
    private Color currentColor1;

    private int pixelColor2;
    private Color currentColor2;



//    private boolean isFailed = false;





    public CircuitBreakerOne() {
        this.setLayout(null);
        this.setVisible(false);
        this.circuitBreaker1 = new ImageIcon("src/main/java/resources/CircuitBreaker1.png");
        this.panelHeight = this.circuitBreaker1.getIconHeight();
        this.panelWidth = this.circuitBreaker1.getIconWidth();
        this.setSize(panelWidth, panelHeight);


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
        if (backgroundImage != null) {
//            this.circuitBreaker1.paintIcon(null, graphics, 0, 0);
            graphics.drawImage(this.backgroundImage, 0, 0, this.panelWidth , this.panelHeight, null);
        }

        graphics.setColor(Color.GREEN);
        for (Point p : trail1) {
            graphics.fillRect(p.x, p.y, 5, 5);

        }
        if(upPressed) {
            yOfMoving1 -= speed;
        } else if (downPressed) {
            yOfMoving1 += speed;
        } else if (leftPressed) {
            xOfMoving1 -= speed;
        } else if (rightPressed) {
            xOfMoving1 += speed;
        }



        // שמור את המיקום הנוכחי לשובל
        trail1.add(new Point(xOfMoving1, yOfMoving1));

        graphics.setColor(Color.RED);
        for (Point p : trail2) {
            graphics.fillRect(p.x, p.y, 5, 5);
        }
        if(wPressed) {
            yOfMoving2 -= speed;
        } else if (sPressed) {
            yOfMoving2 += speed;
        } else if (aPressed) {
            xOfMoving2 -= speed;
        } else if (dPressed) {
            xOfMoving2 += speed;
        }



        // שמור את המיקום הנוכחי לשובל
        trail2.add(new Point(xOfMoving2, yOfMoving2));


        if (checkCollision() && !isFailed) {
            isFailed = true;
            gameCondition = false;
//            JOptionPane.showMessageDialog(null, "פגעת בקיר!");

        }



    }






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
            case KeyEvent.VK_S ->{
                sPressed = true;
                wPressed = false;
                dPressed = false;
                aPressed = false;
            }
            case KeyEvent.VK_W -> {
                wPressed = true;
                sPressed = false;
                dPressed = false;
                aPressed = false;
            }
            case KeyEvent.VK_D -> {
                dPressed = true;
                sPressed = false;
                wPressed = false;
                aPressed = false;
            }
            case KeyEvent.VK_A ->{
                aPressed = true;
                sPressed = false;
                wPressed = false;
                dPressed = false;
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
        int tolerance = 25;   // טווח קרוב לצבע השחור

        if (xOfMoving1 > 0 && yOfMoving1 > 0 && xOfMoving1 < this.panelWidth && yOfMoving1 < this.panelHeight || xOfMoving2 > 0 && yOfMoving2 > 0 && xOfMoving2 < this.panelWidth && yOfMoving2 < this.panelHeight){  //תנאי שהבדיקה תתבצע בתוך תחומי התמונה בלבד ולא מחוצה לה, שאחרת יהיו שגיאות

           pixelColor1 = backgroundImage.getRGB(xOfMoving1, yOfMoving1);
           currentColor1 = new Color(pixelColor1);

            pixelColor2 = backgroundImage.getRGB(xOfMoving2, yOfMoving2);
            currentColor2 = new Color(pixelColor2);


            if (isColorCloseToBlack(currentColor1, tolerance) || isColorCloseToBlack(currentColor2, tolerance)) { // אם פגע במחיצה
               return true;
           }
       }


        return false;
    }



//    private boolean checkCollision2() {
//        int tolerance = 30;   // טווח קרוב לצבע השחור
//
//        if (xOfMoving2 > 0 && yOfMoving2 > 0 && xOfMoving2 < this.panelWidth && yOfMoving2 < this.panelHeight) {  //תנאי שהבדיקה תתבצע בתוך תחומי התמונה בלבד ולא מחוצה לה, שאחרת יהיו שגיאות
//
//            pixelColor2 = backgroundImage.getRGB(xOfMoving2, yOfMoving2);
//            currentColor2 = new Color(pixelColor2);
//
//
//            // טווח קרוב לצבע השחור
//            if (isColorCloseToBlack(currentColor2, tolerance)) { // אם פגע במחיצה
//                return true;
//            }
//        }
//        return false;
//    }

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
