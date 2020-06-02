package base_knowledge.network.nio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.CRC32;

/**
 * FILE-NIO
 */
public class FileNioTest {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("logs/error.log");
        byte[] bytes = Files.readAllBytes(path);
        System.out.println(new String(bytes, StandardCharsets.UTF_8));

        List<String> strings = Files.readAllLines(path, StandardCharsets.UTF_8);
        System.out.println(strings.size() > 0 ? strings.get(0) : "没有内容！");

        System.out.println(Long.toBinaryString(checksumInputStream(path)));
        System.out.println(Long.toBinaryString(checksumBufferedInputStream(path)));
        System.out.println(Long.toBinaryString(checksumRandomAccessFile(path)));
        System.out.println(Long.toHexString(checksumMapperFile(path)));

    }

    private static long checksumInputStream(Path filename) throws IOException {
        try (InputStream inputStream = Files.newInputStream(filename);){
            CRC32 crc32 = new CRC32();
            int c;
            while ((c = inputStream.read()) != -1) {
                crc32.update(c);
            }
            return crc32.getValue();
        }
    }

    private static long checksumBufferedInputStream(Path filename) throws IOException {
        try (InputStream buffer = new BufferedInputStream(Files.newInputStream(filename))){
            CRC32 crc32 = new CRC32();
            int c;
            while ((c = buffer.read()) != -1) {
                crc32.update(c);
            }
            return crc32.getValue();
        }
    }

    private static long checksumRandomAccessFile(Path filename) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(filename.toFile(), "r")){
            long len = file.length();
            CRC32 crc32 = new CRC32();

            for (long i = 0; i < len; i++) {
                file.seek(i);
                int c = file.readByte();
                crc32.update(c);
            }
            return crc32.getValue();
        }
    }

    private static long checksumMapperFile(Path filename) throws IOException {
        try (FileChannel fileChannel = FileChannel.open(filename)){
            CRC32 crc32 = new CRC32();
            int len = (int) fileChannel.size();
            final MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, len);
            for (int i = 0; i < len; i++) {
                int c = map.get(i);
                crc32.update(c);
            }
            return crc32.getValue();
        }
    }
}
