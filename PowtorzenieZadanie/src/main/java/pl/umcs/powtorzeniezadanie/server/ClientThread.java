package pl.umcs.powtorzeniezadanie.server;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private PrintWriter writer;
    private Server server;
    private String clientName = null;

    public Socket getSocket() {
        return socket;
    }

    public ClientThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
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
                server.broadcast(message);
            }
            System.out.println("closed");
            server.removeClient(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String message){
        writer.println(message);
    }

    public String getClientName() {
        return clientName;
    }

    public void login(String name) {
        clientName = name;
    }


}

