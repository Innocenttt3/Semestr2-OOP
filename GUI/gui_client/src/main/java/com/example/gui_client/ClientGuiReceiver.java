package com.example.gui_client;

import pl.umcs.oop.client.ClientReceiver;

public class ClientGuiReceiver implements ClientReceiver {
    HelloController controller= null;

    public void setController(HelloController controller) {
        this.controller = controller;
    }

    @Override
    public void receiveBroadcast(String s, String s1) {
        controller.showBroadcast(s , s1);
    }

    @Override
    public void receiveWhisper(String s, String s1) {

    }

    @Override
    public void receiveFile(String s, long l, String s1) {

    }

    @Override
    public void receiveLoginBroadcast(String s) {

    }

    @Override
    public void receiveLogoutBroadcast(String s) {

    }

    @Override
    public void receiveOnline(String[] strings) {

    }

    @Override
    public void receiveFileProgress(int i) {

    }
}
