package app.artyomd.cam.fd;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

class FaceDetector {
    private CascadeClassifier face_cascade;
    private MatOfRect faces = new MatOfRect();

    public FaceDetector() {
        face_cascade = new CascadeClassifier(FaceDetector.class.getResource("haarcascade_frontalface_default.xml").getPath());
        if (face_cascade.empty()) {
            System.out.println("--(!)Error loading A\n");
        } else {
            System.out.println("Face classifier loooaaaaaded up");
        }
    }

    public void detect(Mat inputFrame) {
        face_cascade.detectMultiScale(inputFrame, faces);
        for (Rect rect : faces.toArray()) {
            Point center = new Point(rect.x + rect.width * 0.5, rect.y + rect.height * 0.5);
            Size size = new Size(rect.width * 0.5, rect.height * 0.5);
            Imgproc.ellipse(inputFrame, center, size, 0, 0, 360, new Scalar(255, 0, 255), 4, 8, 0);
        }
    }
}