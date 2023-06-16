package pl.umcs.powtorzeniezadanie.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Server {
    private ServerSocket serverSocket;
    private List<ClientThread> clients = new ArrayList<>();

    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        Thread processThread = new Thread(() -> {
            while (true) {
                Socket clientSocket;
                try {
                    clientSocket = serverSocket.accept();
                    ClientThread thread = new ClientThread(clientSocket, this);
                    clients.add(thread);
                    thread.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        processThread.start();
    }

    public void removeClient(ClientThread client) {
        clients.remove(client);
    }

    public void broadcast(String message) {
        for (var currentClient : clients)
            currentClient.send(message);
    }

    private Optional<ClientThread> getClient(String clientName) {
        return clients.stream()
                .filter(client -> clientName.equals(client.getClientName()))
                .findFirst();
    }
}