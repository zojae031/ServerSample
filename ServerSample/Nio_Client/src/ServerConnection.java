import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public abstract class ServerConnection extends Thread {
    //서버의  정보
    private static final String SERVER_IP = "192.168.0.7";
    private static final int SERVER_PORT = 5050;

    //Socket 관련 객체
    private Socket socket;
    protected BufferedReader reader;
    protected BufferedWriter writer;


    public ServerConnection() throws IOException {
        socket = new Socket();
        socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT), 2000);
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            receive();
        }
    }

    abstract void send();

    abstract void receive();

    void closeSocket() throws IOException {
        writer.close();
        reader.close();
        socket.close();
    }
}