package com.example.gui_client;

import javafx.application.Platform;
import pl.umcs.oop.client.ClientReceiver;

import java.util.Arrays;

public class ClientGuiReceiver implements ClientReceiver {
    HelloController controller = null;

    public void setController(HelloController controller) {
        this.controller = controller;
    }

    @Override
    public void receiveBroadcast(String sender, String message) {
        Platform.runLater(() -> controller.showBroadcast(sender , message));
    }

    @Override
    public void receiveWhisper(String s, String s1) {

    }

    @Override
    public void receiveFile(String s, long l, String s1) {

    }

    @Override
    public void receiveLoginBroadcast(String s) {
        Platform.runLater(() -> controller.addToClients(s));
    }

    @Override
    public void receiveLogoutBroadcast(String s) {
        Platform.runLater(() ->controller.removeFromClients(s));
    }

    @Override
    public void receiveOnline(String[] strings) {
        Platform.runLater(() ->controller.populationOnlineList(Arrays.stream(strings).toList()));
    }

    @Override
    public void receiveFileProgress(int i) {

    }
}
