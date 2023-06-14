package com.example.gui_client;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import pl.umcs.oop.client.ServerThread;

public class HelloController {

    private ServerThread serverThread;
    private ClientGuiReceiver receiver;

    public HelloController(ServerThread serverThread, ClientGuiReceiver receiver) {
        this.serverThread = serverThread;
        this.receiver = receiver;
        receiver.setController(this);
    }

    public Button sendButton;
    public Button sendFileButton;
    public TextArea outputArea;
    public TextField inputFiled;
    public ListView clientList;
    public ProgressBar fileProgressBar;
    public GridPane mainPage;

    public void send(){
        String text = inputFiled.getText();
        serverThread.broadcast(text);
        inputFiled.clear();
;    }
    public void showBroadcast(String sender, String message){
        outputArea.appendText("\n" + sender + ":" + message);
    }
}