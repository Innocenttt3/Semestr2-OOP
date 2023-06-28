package pl.umcs.oop.client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        ServerThread serverThread = new ServerThread("localhost", 21155);
        serverThread.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter login: ");
        try {
            String line = reader.readLine();
            serverThread.login(line);

            while(true) {
                String command = reader.readLine();
                if(command.startsWith("/")) {
                    String data[] = command.substring(1).split("\\s+", 2);
                    switch(data[0]) {
                        case "online" -> serverThread.online();
                        case "w" -> serverThread.whisper(data[1]);
                        default -> System.out.println("Command was not found");
                    }
                }
                else serverThread.broadcast(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}