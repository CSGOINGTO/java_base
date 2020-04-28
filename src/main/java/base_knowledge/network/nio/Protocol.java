package base_knowledge.network.nio;

import base_knowledge.network.TeaHeader;
import base_knowledge.network.TeaMessage;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public interface Protocol {

    /**
     * 读取信息
     */
    TeaMessage read(SocketChannel channel, ByteBuffer buffer) throws IOException;

    /**
     * 转发消息
     *
     * @param message 转发信息
     */
    void write(SocketChannel socketChannel, ByteBuffer buffer, TeaMessage message) throws IOException;

    /**
     * 端对端发送消息
     *
     * @param header  发送消息目的地
     * @param message 发送消息
     */
    void write(SocketChannel socketChannel, ByteBuffer buffer, TeaHeader header, String message) throws IOException;
}
