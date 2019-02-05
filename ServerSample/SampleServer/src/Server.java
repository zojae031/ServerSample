import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static final String ip = "";
	private static final int port = 5050;
	private ServerSocket socket;
	
	public Server() {
		try {
			socket = new ServerSocket(port);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void open() {
		try {
			String address = InetAddress.getLocalHost().getHostAddress();
			System.out.println("IP : " + address);
			System.out.println("Server Open...");
			while (true) {
				Socket client = socket.accept();
				System.out.println("클라이언트 접속");
				ServerThread thread = new ServerThread(client);
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
