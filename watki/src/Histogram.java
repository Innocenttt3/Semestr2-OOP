import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Histogram {
    public final int threadsAvailable = Runtime.getRuntime().availableProcessors();
    public final int valuesOfSingleBit = 256;

    private ImageChanger imageChanger;
    public Histogram(ImageChanger imageChanger) {
        this.imageChanger = imageChanger;
    }

    public void  print() throws InterruptedException {
        System.out.println(threadsAvailable);

        int singlePart = imageChanger.height / threadsAvailable;
        int[][] histogram = new int[threadsAvailable][valuesOfSingleBit];

        Thread threads[] = new Thread[threadsAvailable];
        for (int i = 0; i < threadsAvailable; ++i) {
            int threadIndex = i;
            threads[i] = new Thread(() -> {
                Arrays.fill(histogram[threadIndex], 0);
                int startline = threadIndex * singlePart;
                int endline = (threadIndex == threadsAvailable - 1) ? imageChanger.height : startline + singlePart;
                for (int y = startline; y < endline; y++) {
                    for (int x = 0; x < imageChanger.width; x++) {
                        int rgb = imageChanger.image.getRGB(x, y);
                        int r = (0xFF0000 & rgb) >> 16;
                        ++histogram[threadIndex][r];
                    }
                }
            });
        }

        for (int i = 0; i < threadsAvailable; ++i)
            threads[i].start();

        for (int i = 0; i < threadsAvailable; ++i)
            threads[i].join();
        int[] finalHistogram = new int[256];
        for (int i = 0; i < threadsAvailable; ++i)
            for (int j = 0; j < 256; ++j)
                finalHistogram[j] += histogram[i][j];

        for (int i = 0; i < 256; ++i)
            System.out.println(i + ", " + finalHistogram[i] + " ");
    }
}

