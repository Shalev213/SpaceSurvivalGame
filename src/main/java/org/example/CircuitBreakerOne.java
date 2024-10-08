package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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


    public CircuitBreakerOne() {
        this.circuitBreaker1 = new ImageIcon("src/main/java/resources/CircuitBreaker1.png");
        this.panelHeight = this.circuitBreaker1.getIconHeight();
        this.panelWidth = this.circuitBreaker1.getIconWidth();
        this.setSize(panelWidth, panelHeight);
        this.setLayout(null);
        this.setVisible(false);



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
            JOptionPane.showMessageDialog(null, "פגעת בקיר!");// isFailed = false  להפוך בלחיצה עליו
            rematch();
        }
//        // צייר את האובייקט עצמו
//        graphics.setColor(Color.RED);
//        graphics.fillRect(x, y, 10, 10);

    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (!isFailed) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN -> {
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
                case KeyEvent.VK_LEFT -> {
                    leftPressed = true;
                    downPressed = false;
                    upPressed = false;
                    rightPressed = false;
                }

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }




    // מתודה שמדמה בדיקת פגיעה בקיר
    private boolean checkCollision() {
        // לדוגמה, אם האובייקט נוגע בקצוות החלון
        if (xOfMoving < 0 || yOfMoving < 0 || xOfMoving > this.panelWidth || yOfMoving > this.panelHeight) {

            return true;
        }
        return false;
    }


    public int getPanelWidth() {
        return panelWidth;
    }

    public int getPanelHeight() {
        return panelHeight;
    }

    public void rematch() {
        upPressed = false;
        downPressed = false;
        rightPressed = false;
        leftPressed = false;

        trail.clear();

    }

}
