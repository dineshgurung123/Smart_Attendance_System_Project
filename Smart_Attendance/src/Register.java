import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;


public class Register {

    Mat matFrame;
    VideoCapture capture;

    Timer timer;

    Register() {

        InitializeCamera();
    }

    public void InitializeCamera() {
        capture = new VideoCapture(0);

        if (!capture.isOpened()) {

            capture.open(0);
        } else {
            startCamera();
        }

    }


    public void startCamera() {
        matFrame = new Mat();

                timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (capture.isOpened()) {
                    capture.read(matFrame);

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

}