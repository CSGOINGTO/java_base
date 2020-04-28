package base_knowledge.network.nio.server;

import base_knowledge.network.nio.TeaCharRoomNioProtocol;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO--Server
 */
public class NioServer {

    public static void main(String[] args) throws IOException {
        TeaCharRoomNioProtocol protocol = TeaCharRoomNioProtocol.getInstance();
        SocketAddress address = new InetSocketAddress("127.0.0.1", 8888);
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.bind(address);
        Selector selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            // 注意：这个方法返回的set不能使用remove方法
//            Set<SelectionKey> keys = selector.keys();
            // 阻塞方法。可以设置等待时间，或者使用selectNow方法
            int select = selector.select();
            if (select == 0) continue;
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                // 连接事件
                if (selectionKey.isAcceptable()) {
                    final SocketChannel accept = server.accept();
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                    System.out.println(accept.getLocalAddress());
                } else if (selectionKey.isReadable()) {
                    final SocketChannel channel = (SocketChannel) selectionKey.channel();
                    channel.configureBlocking(false);
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    protocol.read(channel, byteBuffer);
//                    int read;
//                    while ((read = channel.read(byteBuffer)) > 0) {
//                        byteBuffer.flip();
//                        System.out.println(new String(byteBuffer.array(), 0, read));
//                        byteBuffer.clear();
//                    }
                }
                iterator.remove();
            }
        }
    }
}
