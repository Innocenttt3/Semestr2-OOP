import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageChanger {
    public BufferedImage getImage() {
        return image;
    }

    public BufferedImage image;
    public int height, width;

    public void loadImageFromPath(Path path) {
        File file = new File(path.toString());
        try {
            this.image = ImageIO.read(file);
            this.height = image.getHeight();
            this.width = image.getWidth();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveToImage(Path path) {
        try {
            ImageIO.write(this.image, "jpg", new File(path.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addBrightness(int offset) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int red = (0xFF0000 & rgb) >> 16;
                int green = (0xFF00 & rgb) >> 8;
                int blue = 0xFF & rgb;
                red = addBrightnessToChannel(offset, red);
                green = addBrightnessToChannel(offset, green);
                blue = addBrightnessToChannel(offset, blue);
                rgb = blue | (green << 8) | (red << 16);
                image.setRGB(x, y, rgb);
            }
        }
    }

    public void addBrightnessThreadsSolution(int offset, int cpuCores) throws InterruptedException {
        int singleCorePart = height / cpuCores;
        Thread threads[] = new Thread[cpuCores
                ];
        for (int i = 0; i < cpuCores
                ; ++i) {
            int threadIndex = i;
            threads[i] = new Thread(() -> {
                int startline = threadIndex * singleCorePart;
                int endline = (threadIndex == cpuCores
                        - 1) ?
                        height :
                        startline + singleCorePart;

                for (int y = startline; y < endline; y++) {
                    for (int x = 0; x < width; x++) {
                        int rgb = image.getRGB(x, y);
                        int red = (0xFF0000 & rgb) >> 16;
                        int green = (0xFF00 & rgb) >> 8;
                        int blue = 0xFF & rgb;
                        red = addBrightnessToChannel(offset, red);
                        green = addBrightnessToChannel(offset, green);
                        blue = addBrightnessToChannel(offset, blue);
                        rgb = blue | (green << 8) | (red << 16);
                        image.setRGB(x, y, rgb);
                    }

                }
            });
        }
        for (int i = 0; i < cpuCores
                ; i++) {
            threads[i].start();
        }
        for (Thread thread : threads) {
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

    public void addBrightnessWithMultiThreads(int offset, int coresAvailable) {
        ExecutorService tmpExecutor = Executors.newFixedThreadPool(coresAvailable);
        for (int y = 0; y < height; y++) {
            final int row = y;
            tmpExecutor.execute(() -> {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, row);
                    int r = (0xFF0000 & rgb) >> 16;
                    int g = (0xFF00 & rgb) >> 8;
                    int b = 0xFF & rgb;
                    r = addBrightnessToChannel(offset, r);
                    g = addBrightnessToChannel(offset, g);
                    b = addBrightnessToChannel(offset, b);
                    rgb = b | (g << 8) | (r << 16);
                    image.setRGB(x, row, rgb);
                }
            });
        }
        tmpExecutor.shutdown();
        while (!tmpExecutor.isTerminated()) ;
    }


    public void calculateHistogram() throws InterruptedException {
        int cores = Runtime.getRuntime().availableProcessors();
        int chunk = height / cores;
        int[][] histogram = new int[cores][256];

        Thread threads[] = new Thread[cores];
        for (int i = 0; i < cores; ++i) {
            int threadIndex = i;
            threads[i] = new Thread(() -> {
                Arrays.fill(histogram[threadIndex], 0);
                int startline = threadIndex * chunk;
                int endline = (threadIndex == cores - 1) ?
                        height :
                        startline + chunk;
                for (int y = startline; y < endline; y++) {
                    for (int x = 0; x < width; x++) {
                        int rgb = image.getRGB(x, y);
                        int r = (0xFF0000 & rgb) >> 16;
                        ++histogram[threadIndex][r];
                    }
                }
            });
        }
        for (int i = 0; i < cores; ++i)
            threads[i].start();

        for (int i = 0; i < cores; ++i)
            threads[i].join();
        int[] finalHistogram = new int[256];
        for (int i = 0; i < cores; ++i)
            for (int j = 0; j < 256; ++j)
                finalHistogram[j] += histogram[i][j];

        for (int i = 0; i < 256; ++i)
            System.out.println(i + ", " + finalHistogram[i] + " ");
    }
}


