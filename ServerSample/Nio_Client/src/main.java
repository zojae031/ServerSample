import java.io.IOException;

public class main {
    public static void main(String[] args) {

        try {
            Client client = new Client();
            client.start();
            while (!client.isInterrupted()) {
                client.send();
            }
            System.out.println("종료");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}