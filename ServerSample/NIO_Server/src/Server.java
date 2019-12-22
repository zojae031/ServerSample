/**
 * NIO Socket 예제
 *
 * @author 조재영
 * 참고한 사이트
 * @see https://jungwoon.github.io/java/2019/01/15/NIO-Network/
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Server {
    ServerSocketChannel serverSocketChannel;
    Selector selector;
    ByteBuffer buffer = ByteBuffer.allocate(256);
    List clients = new LinkedList<SocketChannel>();

    public void open() {
        try {
            selector = Selector.open();
            initChannel();

            while (true) {
                selector.selectNow();

                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectionKeySet.iterator();

                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if (key.isAcceptable()) {
                        System.out.println("accept");
                        accept(selector, key, clients);
                    }
                    if (key.isReadable()) {
                        System.out.println("Readable");
                        echoClient(buffer, key, clients);
                    }
                    iter.remove();
                }

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private void initChannel() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(5050));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    private static void accept(Selector selector, SelectionKey key, List<SocketChannel> clients) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        SocketChannel client = server.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
        clients.add(client);
        System.out.println("new client connected..." + client.toString());
    }

    private static void echoClient(ByteBuffer buffer, SelectionKey key, List<SocketChannel> clients) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        try {

            client.read(buffer);
            if (new String(buffer.array()).trim().equals("EXIT")) {
                client.close();
                System.out.println("Not accepting client messages anymore");
            }
            buffer.flip();

            for (SocketChannel user : clients) {
                user.write(buffer);
            }

            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
            client.close();
        }

    }
}
