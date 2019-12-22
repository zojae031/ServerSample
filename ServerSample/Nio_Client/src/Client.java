import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Client extends ServerConnection {
    String result;
    Scanner sc = new Scanner(System.in);

    public Client() throws IOException {

    }

    @Override
    void send() {
        PrintWriter out = new PrintWriter(writer, true);
        String data = sc.nextLine();
        if (data.equals("q")) this.interrupt();
        out.println(data);
        System.out.println("서버로 전송 할 데이터 : " + data);
    }

    @Override
    void receive() {
        try {
            result = reader.readLine();
        } catch (IOException e) {
            System.out.println("서버로 부터 데이터를 받지 못함");
        }
        System.out.println("서버로 부터 받은 결과 : " + result);
    }

}