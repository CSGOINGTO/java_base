package base_knowledge.network.nio.client;

import base_knowledge.network.TeaHeader;
import base_knowledge.network.TeaMessage;
import base_knowledge.network.nio.TeaCharRoomNioProtocol;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
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
        TeaHeader teaHeader = new TeaHeader("127.0.0.1", 8888, "TEST1111");
        TeaCharRoomNioProtocol protocol = TeaCharRoomNioProtocol.getInstance();
        Scanner in = new Scanner(System.in);
        String msg;
        while ((msg = in.nextLine()) != null) {
            protocol.write(client, byteBuffer, new TeaMessage(msg, teaHeader));
//            byteBuffer.put(msg.getBytes(StandardCharsets.UTF_8));
//            byteBuffer.flip();
//            client.write(byteBuffer);
//            byteBuffer.clear();
        }
        client.close();
        in.close();
    }
}
