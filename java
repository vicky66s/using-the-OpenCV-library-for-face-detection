import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetection {
    public static void main(String[] args) {
        // Load the OpenCV native library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Load the pre-trained face detection model
        CascadeClassifier faceCascade = new CascadeClassifier();
        faceCascade.load("haarcascade_frontalface_default.xml");

        // Read the image
        Mat image = Imgcodecs.imread("your_image.jpg");

        // Convert the image to grayscale
        Mat grayImage = new Mat();
        Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);

        // Detect faces in the image
        MatOfRect faceDetections = new MatOfRect();
        faceCascade.detectMultiScale(grayImage, faceDetections);

        // Draw rectangles around the detected faces
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0), 2);
        }

        // Display the image with detected faces
        Imgcodecs.imwrite("detected_faces.jpg", image);
    }
}
