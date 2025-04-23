package opencv;

import org.opencv.core.*;
import org.opencv.imgproc.CLAHE;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class OpenCVProcessor {
    private static Scalar LOWER_GREEN;
    private static Scalar UPPER_GREEN;
    private static Scalar LOWER_BLUE;
    private static Scalar UPPER_BLUE;

    private static final int ROI_SCALE_FACTOR = 2;
    private static VideoCapture camera;
    private static Mat previousFrameMat = null;
    private static int screenHeight;

    static {
        loadHSVConfig();
        initializeCamera();
    }

    private static void loadHSVConfig() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("color_config.properties")) {
            props.load(fis);

            LOWER_GREEN = new Scalar(
                    Double.parseDouble(props.getProperty("green.lower.h")),
                    Double.parseDouble(props.getProperty("green.lower.s")),
                    Double.parseDouble(props.getProperty("green.lower.v"))
            );
            UPPER_GREEN = new Scalar(
                    Double.parseDouble(props.getProperty("green.upper.h")),
                    Double.parseDouble(props.getProperty("green.upper.s")),
                    Double.parseDouble(props.getProperty("green.upper.v"))
            );

            LOWER_BLUE = new Scalar(90, 100, 100);  // Keep defaults
            UPPER_BLUE = new Scalar(130, 255, 255);

        } catch (IOException e) {
            System.err.println("Failed to load HSV config. Using default values.");
            LOWER_GREEN = new Scalar(30, 120, 30);
            UPPER_GREEN = new Scalar(90, 255, 90);
            LOWER_BLUE = new Scalar(90, 100, 100);
            UPPER_BLUE = new Scalar(130, 255, 255);
        }
    }

    private static void initializeCamera() {
        camera = new VideoCapture(0, Videoio.CAP_DSHOW);
        if (!camera.isOpened()) {
            throw new RuntimeException("Failed to open camera!");
        }

        Mat frameMat = new Mat();
        try {
            if (camera.read(frameMat)) {
                screenHeight = frameMat.height();
            } else {
                throw new RuntimeException("Failed to read initial frame from camera!");
            }
        } finally {
            frameMat.release();
        }
    }

    public static int getMarkerPosition(String color, boolean isRight) {
        if (camera == null || !camera.isOpened()) {
            initializeCamera();
        }

        Mat frameMat = new Mat();
        Mat mask = new Mat();
        Mat croppedFrame = new Mat();
        Mat diffFrame = new Mat();
        Mat hsv = new Mat();

        try {
            if (!camera.read(frameMat)) {
                return -1;
            }

            if (previousFrameMat != null) {
                Core.absdiff(previousFrameMat, frameMat, diffFrame);
                Imgproc.cvtColor(diffFrame, diffFrame, Imgproc.COLOR_BGR2GRAY);
                Imgproc.threshold(diffFrame, diffFrame, 30, 255, Imgproc.THRESH_BINARY);
            }

            if (previousFrameMat == null || Core.countNonZero(diffFrame) > 0) {
                Rect roi = calculateROI(frameMat, isRight);
                croppedFrame = new Mat(frameMat, roi);
                resizeFrame(croppedFrame);
                Imgproc.cvtColor(croppedFrame, hsv, Imgproc.COLOR_BGR2HSV);

                // Use CLAHE to improve contrast
                Mat claheImage = new Mat();
                Imgproc.cvtColor(croppedFrame, claheImage, Imgproc.COLOR_BGR2GRAY);
                CLAHE clahe = Imgproc.createCLAHE();
                clahe.setClipLimit(3);
                clahe.apply(claheImage, claheImage);

                if (color.equalsIgnoreCase("green")) {
                    createMask(hsv, mask, LOWER_GREEN, UPPER_GREEN);
                } else if (color.equalsIgnoreCase("blue")) {
                    createMask(hsv, mask, LOWER_BLUE, UPPER_BLUE);
                } else {
                    return -1;
                }

                Imgproc.erode(mask, mask, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5)));
                Imgproc.dilate(mask, mask, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5)));

                previousFrameMat = frameMat.clone();

                return calculateMarkerPosition(mask, croppedFrame.height());
            }
        } finally {
            releaseResources(frameMat, mask, croppedFrame, diffFrame, hsv);
        }
        return -1;
    }

    private static Rect calculateROI(Mat frameMat, boolean isRight) {
        int width = frameMat.width() / 2;
        int height = frameMat.height();
        int x = isRight ? 0 : width;
        int y = 0;
        return new Rect(x, y, width, height);
    }

    private static void resizeFrame(Mat frame) {
        Imgproc.resize(frame, frame, new Size(frame.width() / ROI_SCALE_FACTOR, frame.height() / ROI_SCALE_FACTOR));
    }

    private static void createMask(Mat frame, Mat mask, Scalar lower, Scalar upper) {
        Core.inRange(frame, lower, upper, mask);
    }

    private static int calculateMarkerPosition(Mat mask, int frameHeight) {
        List<MatOfPoint> contours = new java.util.ArrayList<>();
        Imgproc.findContours(mask, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        if (!contours.isEmpty()) {
            MatOfPoint largestContour = findLargestContour(contours);
            return calculateCenterY(largestContour, frameHeight);
        }
        return -1;
    }

    private static MatOfPoint findLargestContour(List<MatOfPoint> contours) {
        MatOfPoint largestContour = contours.get(0);
        double maxArea = Imgproc.contourArea(largestContour);

        for (MatOfPoint contour : contours) {
            double area = Imgproc.contourArea(contour);
            if (area > maxArea) {
                maxArea = area;
                largestContour = contour;
            }
        }
        return largestContour;
    }

    private static int calculateCenterY(MatOfPoint contour, int frameHeight) {
        Moments moments = Imgproc.moments(contour);
        if (moments.get_m00() != 0) {
            int centerY = (int) (moments.get_m01() / moments.get_m00());
            return (centerY < frameHeight / 2) ? 0 : 2;
        }
        return -1;
    }

    private static void releaseResources(Mat... mats) {
        for (Mat mat : mats) {
            if (mat != null) {
                mat.release();
            }
        }
    }

    public static void release() {
        if (camera != null) {
            camera.release();
        }
    }
}
