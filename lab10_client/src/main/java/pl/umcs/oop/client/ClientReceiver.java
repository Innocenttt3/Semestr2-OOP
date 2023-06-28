package pl.umcs.oop.client;

public interface ClientReceiver {
    void receiveBroadcast(String sender, String message);
    void receiveWhisper(String sender, String message);
    void receiveLoginBroadcast(String sender);
    void receiveLogoutBroadcast(String sender);
    void receiveOnline(String[] clientNames);

}
