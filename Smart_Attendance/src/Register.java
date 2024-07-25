import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import java.util.Timer;
import java.util.TimerTask;

public class Register {

    private Mat matFrame;
    private VideoCapture capture;
    private MatOfRect faces;
    private Timer timer;
    private CascadeClassifier facedetector;

    public Register() {
        initializeClassifier();
        initializeCamera();
    }

    public void initializeCamera() {
        capture = new VideoCapture(0);

        if (!capture.isOpened()) {
            capture.open(0);
        } else {
            faces = new MatOfRect();
            startCamera();
        }

        // Schedule task to capture image after 5 seconds
        Timer captureTimer = new Timer();
        captureTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Check if camera is opened and capture a frame
                if (capture.isOpened()) {
                    Mat captureFrame = new Mat();
                    if (capture.read(captureFrame)) {
                        // Save the captured frame to a file (you can modify this part based on your requirement)
                        String filename = "captured_image.jpg";
                        Imgcodecs.imwrite(filename, captureFrame);
                        System.out.println("Image captured and saved as " + filename);

                        // Stop the capture timer after capturing the image
                        captureTimer.cancel();
                        captureTimer.purge();

                        // Release the resources
                        capture.release();
                    }
                }
            }
        }, 5000); // Capture image after 5 seconds (5000 milliseconds)
    }

    public void startCamera() {
        matFrame = new Mat();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (capture.isOpened()) {
                    capture.read(matFrame);
                    facedetector.detectMultiScale(matFrame, faces);

                    // Draw rectangle around faces
                    for (Rect face : faces.toArray()) {
                        Imgproc.rectangle(matFrame, new org.opencv.core.Point(face.x, face.y), new org.opencv.core.Point(face.x + face.width, face.y + face.height), new Scalar(0, 255, 0), 2);
                    }

                    if (!matFrame.empty()) {
                        // Display the frame using HighGui (OpenCV)
                        HighGui.imshow("Webcam Feed", matFrame);

                        // Allow OpenCV GUI to process events
                        HighGui.waitKey(1); // 1 millisecond delay to process GUI events
                    }
                }
            }
        }, 0, 30); // Initial delay of 0 milliseconds, repeat every 30 milliseconds
    }

    public void initializeClassifier() {
        facedetector = new CascadeClassifier("HAAR file/haarcascade_frontalface_default.xml");

        if (facedetector.empty()) {
            System.out.println("classifier not loaded");
        } else {
            System.out.println("classifier loaded");
        }
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // Load OpenCV native library

        Register register = new Register();
    }
}