package base_knowledge.network.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * NIO-Client
 */
public class NioClient {
    public static void main(String[] args) throws IOException {
        SocketAddress address = new InetSocketAddress("127.0.0.1", 8888);
        SocketChannel client = SocketChannel.open(address);
        client.configureBlocking(false);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        Scanner in = new Scanner(System.in);
        String msg;
        while ((msg = in.nextLine()) != null) {
            byteBuffer.put(msg.getBytes(StandardCharsets.UTF_8));
            byteBuffer.flip();
            client.write(byteBuffer);
            byteBuffer.clear();
        }
        client.close();
        in.close();
    }
}
