package app.artyomd.cam.fd;

import org.opencv.core.Mat;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

@SuppressWarnings("WeakerAccess")
class FacePanel extends JPanel {

    private BufferedImage image;

    public FacePanel() {
        super();
    }

    public void matToBufferedImage(Mat matrix) {
        if (image == null) {
            image = new BufferedImage(matrix.width(), matrix.height(), BufferedImage.TYPE_3BYTE_BGR);
        }
        matrix.get(0, 0, ((DataBufferByte) image.getRaster().getDataBuffer()).getData());
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (this.image == null) {
            return;
        }
        graphics.drawImage(this.image, 0, 0, this.image.getWidth(), this.image.getHeight(), null);
    }
}