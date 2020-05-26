package base_knowledge.network.zeroCopy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

/**
 * netty零拷贝机制：减少了不同容器之间数据的拷贝，直接复用地址，提升性能
 */
public class ZeroCopyDemo {
    public static void main(String[] args) {
        ZeroCopyDemo.wrapTest();
        ZeroCopyDemo.sliceTest();
        ZeroCopyDemo.compositeTest();
    }

    public static void wrapTest() {
        byte[] bytes = {1, 2, 3, 4, 5};
        ByteBuf byteBuf = Unpooled.wrappedBuffer(bytes);
        System.out.println(byteBuf.getByte(3));
        bytes[3] = 6;
        System.out.println(byteBuf.getByte(3));
    }

    public static void sliceTest() {
        ByteBuf byteBuf = Unpooled.wrappedBuffer("hello".getBytes());
        ByteBuf newBuffer = byteBuf.slice(1, 2);
        System.out.println((char) newBuffer.getByte(0));
        System.out.println((char) newBuffer.getByte(1));
        newBuffer.unwrap();
        System.out.println(newBuffer);
    }

    public static void compositeTest() {
        ByteBuf buf1 = Unpooled.buffer(3);
        buf1.writeByte(1);
        ByteBuf buf2 = Unpooled.buffer(4);
        buf2.writeByte(4);
        final CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();
        final CompositeByteBuf newBuf = compositeByteBuf.addComponents(true, buf1, buf2);
        System.out.println(newBuf);
    }
}
