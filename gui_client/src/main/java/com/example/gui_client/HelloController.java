package com.example.gui_client;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import pl.umcs.oop.client.ServerThread;

import java.util.List;

public class HelloController {

    private ServerThread serverThread;
    private ClientGuiReceiver receiver;

    public HelloController(ServerThread serverThread, ClientGuiReceiver receiver) {
        this.serverThread = serverThread;
        this.receiver = receiver;
        receiver.setController(this);
    }

    public Button sendButton;
    public TextArea outputArea;
    public TextField inputFiled;
    public ListView clientList;
    public GridPane mainPage;

    public void send(){
        String text = inputFiled.getText();
        serverThread.broadcast(text);
        inputFiled.clear();
;    }
    public void showBroadcast(String sender, String message) {
        outputArea.appendText(String.format("\n" + sender + message));
    }
    public void addToClients(String clientName) {
        clientList.getItems().add(clientName);
    }

    public void removeFromClients(String clientName) {
        clientList.getItems().remove(clientName);
    }

    public void populationOnlineList(List<String> userNames) {
        clientList.getItems().clear();
        userNames.stream()
                .forEach(name -> clientList.getItems().add(name));
    }
    public void initialize() {
        serverThread.online();
    }


}