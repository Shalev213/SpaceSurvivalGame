package opencv;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class OpenCVProcessor {
    private static VideoCapture camera;
    private static int screenWidth;
    private static Point lastKnownPosition = new Point(-1, -1); // שמירת מיקום אחרון

    // בלוק אתחול סטטי
    static {
        camera = new VideoCapture(0, Videoio.CAP_DSHOW); // שימוש בממשק DirectShow
        if (!camera.isOpened()) {
            throw new RuntimeException("Failed to open camera!");
        }

        Mat frameMat = new Mat();
        try {
            if (camera.read(frameMat)) {
                screenWidth = frameMat.width();
            } else {
                throw new RuntimeException("Failed to read initial frame from camera!");
            }
        } finally {
            frameMat.release(); // שחרור משאבים
        }
    }

    public static int getMarkerPosition() {
        Mat frameMat = new Mat();
        Mat hsv = new Mat();
        Mat mask = new Mat();

        try {
            // קריאה מהירה מהמצלמה
            if (!camera.read(frameMat)) {
                return -1;
            }

            // עיבוד רק אזור עניין (ROI) דינמי
            Rect roi = getDynamicROI(frameMat);
            Mat croppedFrame = new Mat(frameMat, roi);

            // הקטנת רזולוציה
            Imgproc.resize(croppedFrame, croppedFrame, new Size(croppedFrame.width() / 2, croppedFrame.height() / 2));

            // המרת צבעים למרחב HSV
            Imgproc.cvtColor(croppedFrame, hsv, Imgproc.COLOR_BGR2HSV);

            // הגדרת טווח הצבע הירוק
            Scalar lowerGreen = new Scalar(35, 100, 100);
            Scalar upperGreen = new Scalar(85, 255, 255);
            Core.inRange(hsv, lowerGreen, upperGreen, mask);

            // חיפוש קונטורים וחישוב מהיר של הגדול ביותר
            java.util.List<MatOfPoint> contours = new java.util.ArrayList<>();
            Imgproc.findContours(mask, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

            MatOfPoint largestContour = null;
            double maxArea = 0;
            int centerX = -1;

            for (MatOfPoint contour : contours) {
                double area = Imgproc.contourArea(contour);
                if (area > maxArea) {
                    maxArea = area;
                    largestContour = contour;
                }
            }

            // חישוב מרכז הקונטור הגדול ביותר אם נמצא
            if (largestContour != null && maxArea > 500) { // שטח מינימלי לסינון רעשים
                Moments moments = Imgproc.moments(largestContour);
                if (moments.get_m00() != 0) {
                    centerX = (int) (moments.get_m10() / moments.get_m00());
                    lastKnownPosition = new Point(centerX, moments.get_m01() / moments.get_m00());
                }
            }

            // חישוב מיקום יחסי במסך
            if (centerX != -1) {
                if (centerX < croppedFrame.width() / 3) {
                    return 0; // שמאל
                } else if (centerX < 2 * croppedFrame.width() / 3) {
                    return 1; // אמצע
                } else {
                    return 2; // ימין
                }
            }

            return -1; // לא נמצא מרקר ירוק
        } finally {
            frameMat.release();
            hsv.release();
            mask.release();
        }
    }

    private static Rect getDynamicROI(Mat frame) {
        if (lastKnownPosition.x != -1) {
            int roiSize = 100; // גודל ROI
            int x = Math.max(0, (int) lastKnownPosition.x - roiSize / 2);
            int y = Math.max(0, (int) lastKnownPosition.y - roiSize / 2);
            int width = Math.min(roiSize, frame.width() - x);
            int height = Math.min(roiSize, frame.height() - y);
            return new Rect(x, y, width, height);
        } else {
            // חזרה ל-ROI המרכזי
            return new Rect(frame.width() / 4, frame.height() / 4, frame.width() / 2, frame.height() / 2);
        }
    }

    public static void release() {
        if (camera != null) {
            camera.release();
        }
    }
}
