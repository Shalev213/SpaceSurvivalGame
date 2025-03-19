package opencv;

import org.opencv.core.*;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CameraPreview extends JPanel {
    private VideoCapture camera;
    private JLabel imageLabel;
    private JSlider slider;
    private boolean running;
    private static int threshold;
    private int windowWidth = 800;
    private int windowHeight = 600;
    private JButton startButton;

    public CameraPreview() {
        camera = new VideoCapture(0);
        if (!camera.isOpened()) {
            System.out.println("Error: Cannot open camera");
            return;
        }

        this.setSize(this.windowWidth, this.windowHeight);

//        JFrame frame = new JFrame("Camera Preview");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(800, 600);
//        frame.setLayout(new BorderLayout());

        this.imageLabel = new JLabel();
//        this.imageLabel.setBounds();
        add(this.imageLabel);

        this.slider = new JSlider(0, 255, 50);
        this.setBounds(50, 450, 400, 100);
        add(this.slider);

        this.startButton = new JButton("Start");
        this.startButton.setBounds(300, 550, 50, 50);
        this.startButton.setFont(new Font("Arial", Font.BOLD, 15));
        add(this.startButton);

//        setVisible(true);

        running = true;
        startCamera();
    }

    private void startCamera() {
        new Thread(() -> {
            Mat frame = new Mat();
            Mat hsv = new Mat();
            Mat mask = new Mat();

            while (running) {
                if (camera.read(frame)) {
                    // הפיכת התמונה כמו מראה
                    Core.flip(frame, frame, 1);

                    Imgproc.cvtColor(frame, hsv, Imgproc.COLOR_BGR2HSV);

                    threshold = slider.getValue(); // הערך מהסליידר קובע את עוצמת זיהוי הירוק
                    Scalar lowerGreen = new Scalar(35, threshold, threshold);
                    Scalar upperGreen = new Scalar(85, 255, 255);

                    Core.inRange(hsv, lowerGreen, upperGreen, mask);

                    // הצגת המסכה בלבד (רק האזורים הירוקים יהיו בולטים)
                    ImageIcon image = new ImageIcon(matToBufferedImage(mask));
                    imageLabel.setIcon(image);
                }
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            camera.release();
        }).start();
    }

    private BufferedImage matToBufferedImage(Mat mat) {
        int width = mat.width(), height = mat.height();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        byte[] data = new byte[width * height];
        mat.get(0, 0, data);
        image.getRaster().setDataElements(0, 0, width, height, data);
        return image;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public int getThreshold() {
        return this.threshold;
    }
    public  void release() {
        if (camera != null) {
            camera.release();
        }
    }
}
