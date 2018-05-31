package app.artyomd.cam.fd;

import java.awt.*;
import javax.swing.*;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class CamFD {
    public static void main(String arg[]) {
        System.out.println(Core.VERSION);
        System.load(CamFD.class.getResource("libopencv_java400.so").getPath());

        JFrame frame = new JFrame("WebCam Capture - Face detection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FacePanel facePanel = new FacePanel();
        frame.add(facePanel, BorderLayout.CENTER);
        frame.setVisible(true);

        Mat webCamImage = new Mat();
        VideoCapture webCam = new VideoCapture(0);
        FaceDetector faceDetector = new FaceDetector();

        if (webCam.isOpened()) {
            webCam.read(webCamImage);
            frame.setSize(webCamImage.width(), webCamImage.height());

            while (true) {
                webCam.read(webCamImage);
                if (!webCamImage.empty()) {
                    faceDetector.detect(webCamImage);
                    facePanel.matToBufferedImage(webCamImage);
                    facePanel.repaint();
                } else {
                    System.out.println(" --(!) No captured frame from webcam !");
                    break;
                }
            }
        }
        webCam.release();
    }
}