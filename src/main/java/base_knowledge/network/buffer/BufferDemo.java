package base_knowledge.network.buffer;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

@Slf4j
public class BufferDemo {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4); // 堆内内存
//        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(4); // 堆外内存
        log.info("ByteBuffer初始化：capacity容量：{}，position位置：{}，limit位置：{}",
                byteBuffer.capacity(),
                byteBuffer.position(),
                byteBuffer.limit());
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 2);
        byteBuffer.put((byte) 3);
        log.info("写入三个字节后：capacity容量：{}，position位置：{}，limit位置：{}",
                byteBuffer.capacity(),
                byteBuffer.position(),
                byteBuffer.limit());
        byteBuffer.flip();
        log.info("开启读取模式：capacity容量：{}，position位置：{}，limit位置：{}",
                byteBuffer.capacity(),
                byteBuffer.position(),
                byteBuffer.limit());
        final byte a = byteBuffer.get();
        log.info("读取的第一个数据：{}", a);
        final byte b = byteBuffer.get();
        log.info("读取的第一个数据：{}", b);
        log.info("读取2个字节的数据：capacity容量：{}，position位置：{}，limit位置：{}",
                byteBuffer.capacity(),
                byteBuffer.position(),
                byteBuffer.limit());
        byteBuffer.compact(); // 清除已阅读的数据
        log.info("清除已阅读的数据：capacity容量：{}，position位置：{}，limit位置：{}",
                byteBuffer.capacity(),
                byteBuffer.position(),
                byteBuffer.limit());
        byteBuffer.put((byte) 4);
        byteBuffer.put((byte) 5);
        log.info("最终情况：capacity容量：{}，position位置：{}，limit位置：{}",
                byteBuffer.capacity(),
                byteBuffer.position(),
                byteBuffer.limit());
    }
}
