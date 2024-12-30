package opencv;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class OpenCVProcessor {
    private static final Scalar LOWER_GREEN = new Scalar(30, 120, 30);  // ערכים יותר מדויקים לירוק
    private static final Scalar UPPER_GREEN = new Scalar(90, 255, 90);  // טווח מצומצם יותר

    private static final int ROI_SCALE_FACTOR = 2;
    private static VideoCapture camera;
    private static int screenHeight;

    // חסימת יצירת מופעים של המחלקה
    private OpenCVProcessor() {}

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

    public static int getMarkerPosition() {
        Mat frameMat = new Mat();
        Mat mask = new Mat();
        Mat croppedFrame = new Mat();

        try {
            if (!camera.read(frameMat)) {
                return -1;
            }

            Rect roi = calculateROI(frameMat);
            croppedFrame = new Mat(frameMat, roi);
            resizeFrame(croppedFrame);
            createMask(croppedFrame, mask);

            return calculateMarkerPosition(mask, croppedFrame.height());
        } finally {
            releaseResources(frameMat, mask, croppedFrame);
        }
    }

    private static Rect calculateROI(Mat frameMat) {
        int x = frameMat.width() / 4;
        int y = frameMat.height() / 4;
        int width = frameMat.width() / 2;
        int height = frameMat.height() / 2;
        return new Rect(x, y, width, height);
    }

    private static void resizeFrame(Mat frame) {
        Imgproc.resize(frame, frame, new Size(frame.width() / ROI_SCALE_FACTOR, frame.height() / ROI_SCALE_FACTOR));
    }

    private static void createMask(Mat frame, Mat mask) {
        // כברירת מחדל, OpenCV משתמש ב-BGR ולכן אין צורך להמיר ל-RGB
        Core.inRange(frame, LOWER_GREEN, UPPER_GREEN, mask);
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
//            if (centerY < frameHeight / 3) {
//                return 0; // חלק עליון
//            } else if (centerY < 2 * frameHeight / 3) {
//                return 1; // אמצע
//            } else {
//                return 2; // חלק תחתון
//            }
            if (centerY < frameHeight / 2) {
                return 0; // חלק עליון
            } else {
                return 2; // חלק תחתון
            }
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
