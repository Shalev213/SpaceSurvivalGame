package opencv;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class OpenCVProcessor {
    private static final Scalar LOWER_GREEN = new Scalar(30, 120, 30);
    private static final Scalar UPPER_GREEN = new Scalar(90, 255, 90);
    private static final Scalar LOWER_YELLOW = new Scalar(20, 100, 100);
    private static final Scalar UPPER_YELLOW = new Scalar(30, 255, 255);

    private static final int ROI_SCALE_FACTOR = 2;
    private static VideoCapture camera;
    private static Mat previousFrameMat = null;
    private static int screenHeight;

    static {
        initializeCamera();
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

        try {
            if (!camera.read(frameMat)) {
                return -1;
            }

            if (previousFrameMat != null) {
                // חישוב ההבדל בין התמונות
                Core.absdiff(previousFrameMat, frameMat, diffFrame);

                // המרת ההבדל למסיכה בינארית (רק השינויים)
                Imgproc.cvtColor(diffFrame, diffFrame, Imgproc.COLOR_BGR2GRAY);
                Imgproc.threshold(diffFrame, diffFrame, 30, 255, Imgproc.THRESH_BINARY);
            }

            // אם לא הייתה תמונה קודמת, תמשיך כפי שהיה
            if (previousFrameMat == null || Core.countNonZero(diffFrame) > 0) {
                Rect roi = calculateROI(frameMat, isRight);
                croppedFrame = new Mat(frameMat, roi);
                resizeFrame(croppedFrame);

                if (color.equalsIgnoreCase("green")) {
                    createMask(croppedFrame, mask, LOWER_GREEN, UPPER_GREEN);
                } else if (color.equalsIgnoreCase("yellow")) {
                    createMask(croppedFrame, mask, LOWER_YELLOW, UPPER_YELLOW);
                } else {
                    return -1;
                }

                previousFrameMat = frameMat.clone(); // שמירת התמונה הקודמת

                return calculateMarkerPosition(mask, croppedFrame.height());
            }
        } finally {
            releaseResources(frameMat, mask, croppedFrame, diffFrame);
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
        java.util.List<MatOfPoint> contours = new java.util.ArrayList<>();
        Imgproc.findContours(mask, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        if (!contours.isEmpty()) {
            MatOfPoint largestContour = findLargestContour(contours);
            return calculateCenterY(largestContour, frameHeight);
        }
        return -1;
    }

    private static MatOfPoint findLargestContour(java.util.List<MatOfPoint> contours) {
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
