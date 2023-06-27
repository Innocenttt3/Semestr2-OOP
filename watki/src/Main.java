import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        String exampleOfPhoto = "/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/watki/image.jpg";
//        String outputPath1= "/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/watki/image-copy1.jpg";
//        String outputPath2= "/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/watki/image-copy2.jpg";
//        String outputPath3= "/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/watki/image-copy3.jpg";
//        Path pathOfImage = Paths.get(exampleOfPhoto);
//        Path pathOfOutputImage = Paths.get(outputPath1);
//        Path pathOfOutputImage2 = Paths.get(outputPath2);
//        Path pathOfOutputImage3 = Paths.get(outputPath3);
//        ImageChanger image = new ImageChanger();
//
//        image.loadImageFromPath(pathOfImage);
//        long begin1 = System.currentTimeMillis();
//        image.addBrightness(100);
//        long end1 = System.currentTimeMillis();
//        image.saveToImage(pathOfOutputImage);
//
//        image.loadImageFromPath(pathOfImage);
//        int cpuCoresAvailable = Runtime.getRuntime().availableProcessors();
//        long begin2 = System.currentTimeMillis();
//        image.addBrightnessThreadsSolution(-100, cpuCoresAvailable);
//        long end2 = System.currentTimeMillis();
//        image.saveToImage(pathOfOutputImage2);
//
//        image.loadImageFromPath(pathOfImage);
//        long begin3 = System.currentTimeMillis();
//        image.addBrightnessWithMultiThreads(100, cpuCoresAvailable);
//        long end3 = System.currentTimeMillis();
//        image.saveToImage(pathOfOutputImage3);
//        Histogram testHistogram = new Histogram(image);
//        testHistogram.print();
//
//
//        System.out.print("time1:");
//        System.out.print(end1-begin1);
//        System.out.print(" vs time2:");
//        System.out.print(end2-begin2);
//        System.out.print(" vs time3:");
//        System.out.print(end3-begin3);

        BufferedImage image2 = ImageIO.read(new File(exampleOfPhoto));
        BufferedImage ciexyzImage = ImageConversion.convertToCIEXYZ(image2);
        BufferedImage rgbImage = ImageConversion.convertToRGB(ciexyzImage);
        String ciexyzImagePath = "/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/watki/image-ciexyz.jpg";
        String rgbImagePath = "/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/watki/image-rgb.jpg";
        ImageIO.write(ciexyzImage, "jpg", new File(ciexyzImagePath));
        ImageIO.write(rgbImage, "jpg", new File(rgbImagePath));


    }
}