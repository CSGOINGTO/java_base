package base_knowledge.network.nio;

import base_knowledge.network.TeaHeader;
import base_knowledge.network.TeaMessage;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

@Slf4j
public class TeaCharRoomNioProtocol implements Protocol {

    private static volatile TeaCharRoomNioProtocol teaCharRoomNioProtocol;

    private TeaCharRoomNioProtocol() {
    }

    public static TeaCharRoomNioProtocol getInstance() {
        if (teaCharRoomNioProtocol != null) {
            return teaCharRoomNioProtocol;
        }
        synchronized (TeaCharRoomNioProtocol.class) {
            if (teaCharRoomNioProtocol == null) {
                teaCharRoomNioProtocol = new TeaCharRoomNioProtocol();
            }
        }
        return teaCharRoomNioProtocol;
    }

    @Override
    public TeaMessage read(SocketChannel channel, ByteBuffer buffer) throws IOException {
        while (channel.read(buffer) > 0) {
            buffer.flip();
            log.info("{}", (TeaMessage) toObject(buffer.array()));
        }
        buffer.clear();
        return null;
    }

    @Override
    public void write(SocketChannel socketChannel, ByteBuffer buffer, TeaMessage message) throws IOException {
        buffer.put(toBytes(message));
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();
    }

    @Override
    public void write(SocketChannel socketChannel, ByteBuffer buffer, TeaHeader header, String message) throws IOException {
        buffer.put(toBytes(new TeaMessage(message, header)));
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();
    }

    private static Object toObject(byte[] bytes) {
        Object o = null;
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            o = objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;
    }

    private static byte[] toBytes(Object o) {
        byte[] bytes = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(o);
            objectOutputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
