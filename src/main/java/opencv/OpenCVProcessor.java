package opencv;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class OpenCVProcessor {
    private static final Scalar LOWER_GREEN = new Scalar(30, 120, 30);  // ערכים מדויקים לירוק
    private static final Scalar UPPER_GREEN = new Scalar(90, 255, 90);
    private static final Scalar LOWER_ORANGE = new Scalar(5, 100, 100);  // טווח תחתון לכתום
    private static final Scalar UPPER_ORANGE = new Scalar(15, 255, 255); // טווח עליון לכתום

    private static VideoCapture camera;

    // חסימת יצירת מופעים של המחלקה
    private OpenCVProcessor() {
    }

    static {
        initializeCamera();
    }

    private static void initializeCamera() {
        camera = new VideoCapture(0, Videoio.CAP_DSHOW);
        if (!camera.isOpened()) {
            throw new RuntimeException("Failed to open camera!");
        }
    }

    public static int getMarkerPosition(int indexPlayer) {
        Mat frameMat = new Mat();
        Mat mask = new Mat();

        try {
            // קריאת פריים מהמצלמה
            if (!camera.read(frameMat)) {
                return -1;
            }

            // הקטנת התמונה
            resizeFrame(frameMat);

            // יצירת מסכה עבור השחקן
            createMask(frameMat, mask, indexPlayer);

            // חישוב מיקום הסמן
            return calculateMarkerPosition(mask, frameMat.height());
        } finally {
            releaseResources(frameMat, mask);
        }
    }

    private static void resizeFrame(Mat frame) {
        // הקטנת התמונה לחיסכון בעיבוד
        Imgproc.resize(frame, frame, new Size(frame.width() / 4, frame.height() / 4));
    }

    private static void createMask(Mat frame, Mat mask, int indexPlayer) {
        Mat hsvFrame = new Mat();
        Imgproc.cvtColor(frame, hsvFrame, Imgproc.COLOR_BGR2HSV); // המרה ל-HSV

        // יצירת מסכה על פי צבע השחקן
        switch (indexPlayer) {
            case 1 -> Core.inRange(hsvFrame, LOWER_GREEN, UPPER_GREEN, mask);
            case 2 -> Core.inRange(hsvFrame, LOWER_ORANGE, UPPER_ORANGE, mask); // שינוי לאורנג'
        }
        hsvFrame.release(); // שחרור משאבים
    }

    private static int calculateMarkerPosition(Mat mask, int frameHeight) {
        java.util.List<MatOfPoint> contours = new java.util.ArrayList<>();
        Imgproc.findContours(mask, contours, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

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
