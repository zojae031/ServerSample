import java.io.IOException;

public class main {
    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.initServer();
            server.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
