package opencv;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class OpenCVProcessor {
    private static VideoCapture camera;
    private static int screenWidth;

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

            // עיבוד רק אזור עניין (ROI) קטן יותר, מיותר לעבד את כל התמונה
            Rect roi = new Rect(frameMat.width() / 4, frameMat.height() / 4, frameMat.width() / 2, frameMat.height() / 2);
            Mat croppedFrame = new Mat(frameMat, roi);

            // הקטנת רזולוציה (רק את האזור החיוני)
            // אפשר להקטין יותר אם נרצה לשפר ביצועים עוד יותר
            Imgproc.resize(croppedFrame, croppedFrame, new Size(croppedFrame.width() / 2, croppedFrame.height() / 2));

            // המרת צבעים למרחב HSV
            Imgproc.cvtColor(croppedFrame, hsv, Imgproc.COLOR_BGR2HSV);

            // הגדרת טווח הצבע הירוק
            Scalar lowerGreen = new Scalar(35, 100, 100);  // מינימום צבע ירוק ב-HSV
            Scalar upperGreen = new Scalar(85, 255, 255);  // מקסימום צבע ירוק ב-HSV
            Core.inRange(hsv, lowerGreen, upperGreen, mask);

            // חיפוש קונטורים מהיר (ללא שימוש ב-Moments או ב-Bounding Box)
            java.util.List<MatOfPoint> contours = new java.util.ArrayList<>();
            Imgproc.findContours(mask, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

            if (!contours.isEmpty()) {
                // חיפוש הקונטור הגדול ביותר
                MatOfPoint largestContour = contours.get(0);
                double maxArea = Imgproc.contourArea(largestContour);
                int centerX = -1;

                // נבצע חישוב מהיר של מרכז הקונטור הראשון (כדי לחסוך זמן)
                for (MatOfPoint contour : contours) {
                    double area = Imgproc.contourArea(contour);
                    if (area > maxArea) {
                        maxArea = area;
                        largestContour = contour;
                    }
                }

                // אם נמצא קונטור עם שטח מספיק
                if (largestContour != null) {
                    // קבלת קואורדינטות של מרכז הקונטור
                    Moments moments = Imgproc.moments(largestContour);
                    if (moments.get_m00() != 0) {
                        centerX = (int) (moments.get_m10() / moments.get_m00());
                    }

                    // אם נמצא מיקום
                    if (centerX != -1) {
                        // חישוב המיקום היחסי במסך
                        if (centerX < croppedFrame.width() / 3) {
                            return 0; // שמאל
                        } else if (centerX < 2 * croppedFrame.width() / 3) {
                            return 1; // אמצע
                        } else {
                            return 2; // ימין
                        }
                    }
                }
            }

            return -1; // לא נמצא מרקר ירוק
        } finally {
            frameMat.release();
            hsv.release();
            mask.release();
        }
    }





    public static void release() {
        if (camera != null) {
            camera.release();
        }
    }
}
