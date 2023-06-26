package com.example.kolos2;

import javafx.scene.paint.Color;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.Socket;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Server {
    private ServerSocket serverSocket;

    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        while (true) {
            Socket clientSocket;
            try {
                clientSocket = serverSocket.accept();
                System.out.println("client connected");
                receive(clientSocket);
                blur(FileLister.listFilesInFolder("/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/Kolos2"), 20);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void receive(Socket socket) {
        try {

            DataInputStream input = new DataInputStream(socket.getInputStream());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String timestamp = sdf.format(new Date());
            String fileName = "file_" + timestamp + ".png";
            FileOutputStream output = new FileOutputStream("/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/Kolos2/" + fileName);

            byte[] buffer = new byte[8192];
            int count;
            int receivedSize = 0;
            long fileSize = input.readLong();

            while (receivedSize < fileSize) {
                count = input.read(buffer);
                output.write(buffer, 0, count);
                receivedSize += count;
            }
            output.close();

            System.out.println("File received from client and saved to folder.");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void send(String path, Socket socket) {
        try {
            File file = new File(path);
            FileInputStream input = new FileInputStream(file);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            byte[] buffer = new byte[8192];
            int count;
            output.writeLong(file.length());
            while ((count = input.read(buffer)) != -1)
                output.write(buffer, 0, count);

            output.flush();

            System.out.println("File sent to client.");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void blur(String pathArg, int radius) {
        try {
            String destPath = FileLister.listFilesInFolder()
            BufferedImage image = ImageIO.read(new File(pathArg));
            Path sourcePath = Path.of(pathArg);
            Path destinationPath = Path.of(destPath);
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            File tmpFile = new File(destPath);
            BufferedImage imageBlurred = ImageIO.read(tmpFile);
            int width = image.getWidth();
            int height = image.getHeight();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rSum = 0;
                    int gSum = 0;
                    int bSum = 0;
                    int realRadius = radius * 2 + 1;
                    for (int i = y - radius; i < y + radius; i++) {
                        for (int j = x - radius; j < x + radius; j++) {
                            if ( y - radius < 0 || x - radius < 0) {
                                continue;
                            }

                            if (i == y && j == x) {
                                continue;
                            }
                            int rgb = image.getRGB(j, i);
                            int r = (0xFF0000 & rgb) >> 16;
                            int g = (0xFF00 & rgb) >> 8;
                            int b = 0xFF & rgb;
                            rSum += r;
                            gSum += g;
                            bSum += b;
                        }
                    }
                    rSum /= realRadius;
                    gSum /= realRadius;
                    bSum /= realRadius;
                    int blurredRgb = bSum | (gSum << 8) | (rSum << 16);
                    imageBlurred.setRGB(x, y, blurredRgb);
                    ImageIO.write(imageBlurred , "png", new File("/Users/kamilgolawski/Nauka/Programowanie/Semestr2-OOP/Kolos2/lenay-copy.png"));

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        Server testServer;
        testServer = new Server(21155);
        testServer.listen();
    }


}
