import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageProcessor {

    private BufferedImage image;
    private int height, width;

    public void loadImageFromPath(String path) {
        File file = new File(path);
        try {
            this.image = ImageIO.read(file);
            height = image.getHeight();
            width = image.getWidth();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveToImage(String path) {
        try {
            ImageIO.write(this.image, "jpg", new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addBrightness(int offset) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int r = (0xFF0000 & rgb) >> 16;
                int g = (0xFF00 & rgb) >> 8;
                int b = 0xFF & rgb;
                r = addBrightnessToChannel(offset, r);
                g = addBrightnessToChannel(offset, g);
                b = addBrightnessToChannel(offset, b);
                rgb = b | (g << 8) | (r << 16);
                image.setRGB(x, y, rgb);
            }
        }
    }

    public void addBrightnessWithThreads(int offset) throws InterruptedException {
        int coresAmount = Runtime.getRuntime().availableProcessors();
        int singlePart = height / coresAmount;
        Thread threads[] = new Thread[coresAmount];
        for (int i = 0; i < coresAmount; ++i) {
            int threadIndex = i;
            threads[i] = new Thread(() -> {
                int startline = threadIndex * singlePart;
                int endline = (threadIndex == coresAmount - 1) ?
                        height :
                        startline + singlePart;
                for (int y = startline; y < endline; y++) {
                    for (int x = 0; x < width; x++) {
                        int rgb = image.getRGB(x, y);
                        int r = (0xFF0000 & rgb) >> 16;
                        int g = (0xFF00 & rgb) >> 8;
                        int b = 0xFF & rgb;
                        r = addBrightnessToChannel(offset, r);
                        g = addBrightnessToChannel(offset, g);
                        b = addBrightnessToChannel(offset, b);
                        rgb = b | (g << 8) | (r << 16);
                        image.setRGB(x, y, rgb);
                    }

                }
            });
        }
        for (int i = 0; i < coresAmount; i++){
            threads[i].start();
        }
        for (Thread thread: threads){
            thread.join();
        }

    }

    private int addBrightnessToChannel(int offset, int value) {
        value += offset;
        if (value > 255)
            value = 255;
        else if (value < 0)
            value = 0;
        return value;
    }
}
