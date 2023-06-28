import serverPackage.Server;

public class Main {
    public static void main(String[] args) {
        Server server;
        server = new Server(12345);
        server.listen();
    }
}