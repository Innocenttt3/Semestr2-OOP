package pl.umcs.powtorzeniezadanie.client;

import javafx.scene.paint.Color;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;

public class ServerThread extends Thread {
    private Socket socket;
    private PrintWriter writer;

    public ServerThread(String address, int port) {
        try {
            socket = new Socket(address, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            writer = new PrintWriter(output, true);
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(double x, double y, double radius, Color color) {
        writer.println(x + " " + y + " " + radius + " " + color.toString());
    }
}