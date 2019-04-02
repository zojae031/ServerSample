import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ServerThread extends Thread {
	private Socket client;
	private BufferedReader reader;
	private PrintWriter writer;

	public ServerThread(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		try {
			reader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8")),
					true);
			JsonParser parser = new JsonParser();
			while (true) {
				
				JsonObject data = (JsonObject) parser.parse(reader.readLine());
				int key = data.get("key").getAsInt();
				
				System.out.println(data);
				JsonObject sendData = new JsonObject();
				switch(key) {
				case 1:
					sendData.addProperty("key", key);
					sendData.addProperty("value", "����� 1�Դϴ�");
					break;
				case 2:
					sendData.addProperty("key", key);
					sendData.addProperty("value", "�ι�° ����� �Դϴ�");
					break;
				case 3:
					sendData.addProperty("key", key);
					sendData.addProperty("value", "�� ���� ^_^");
					break;
				}
				System.out.println("���� ������ : "+sendData);
				writer.println(sendData.toString()/*data*/);

			}

		} catch (NullPointerException e) {
			System.out.println("Ŭ���̾�Ʈ ���� ����");
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}
}
