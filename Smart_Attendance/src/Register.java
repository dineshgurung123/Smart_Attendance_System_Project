import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;


public class Register {

    Mat matFrame;
    VideoCapture capture;
    MatOfRect faces;
    Timer timer;
    CascadeClassifier facedetector;

    Register() {
        initializeClassifier();
        InitializeCamera();
    }

    public void InitializeCamera() {
        capture = new VideoCapture(0);

        if (!capture.isOpened()) {

            capture.open(0);
        } else {
            faces = new MatOfRect();
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
                    facedetector.detectMultiScale(matFrame,faces);

                    //draw rectangle around faces
                    for (Rect face : faces.toArray())
                    {
                        Imgproc.rectangle(matFrame,new org.opencv.core.Point(face.x,face.y),new org.opencv.core.Point(face.x+face.width,face.y+face.height), new Scalar(0,255,0),2);
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

    public void initializeClassifier()
    {
        facedetector = new CascadeClassifier("HAAR file/haarcascade_frontalface_default.xml");

        if (facedetector.empty())
        {
            System.out.println("classifier not loaded");
        }
        else
        {
            System.out.println("classifier loaded");
        }
    }

}