package base_knowledge.io.byteIo;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class InputStreamDemo {

    public static void main(String[] args) throws IOException {
//        InputStreamDemo.byteInputStream();
//        InputStreamDemo.stringInputStream();
        InputStreamDemo.fileInputStream();
    }

    private static void byteInputStream() throws IOException {
        byte[] bytes = new byte[]{1, 11, 111, 2, 22, 3, 33, 4, 44, 5, 55, 6, 66};
        InputStream byteInputStream = new ByteArrayInputStream(bytes);
        // 一个一个byte的读取
        int n;
        while ((n = byteInputStream.read()) != -1) {
            System.out.print(n + " ");
        }

        // 重置输入流
        byteInputStream.reset();

        System.out.println();
        System.out.println("----------------------------");

        // 将输入流中的内容全部读取到byte数组中
        byte[] out = new byte[bytes.length];
        byteInputStream.read(out, 0, out.length);
        for (byte b : out) System.out.print(b + " ");

        // 关闭输入流
        byteInputStream.close();
    }

    private static void stringInputStream() throws IOException {
        InputStream stringInputStream = new StringBufferInputStream("string inputStream 测试");
        int n;
        while ((n = stringInputStream.read()) != -1) {
            System.out.print((char) n);
        }

        System.out.println();
        stringInputStream.reset();

        byte[] out = new byte[2];
        while (stringInputStream.read(out) != -1) {
            System.out.print(new String(out, StandardCharsets.UTF_8));
        }

        stringInputStream.close();
    }

    private static void fileInputStream() throws IOException {
        InputStream fileInputStream = new FileInputStream(new File("base_knowledge/src/main/resources/log4j.properties"));
//        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        String line;
//        while ((line = bufferedReader.readLine()) != null) {
//            System.out.println(line);
//        }


//        // 每次读取一个字节
//        int n;
//        while ((n = fileInputStream.read()) != -1) {
//            System.out.print((char) n);
//        }
//
//        fileInputStream.reset();
//
//        // 每次读取两个字节
        byte[] out = new byte[2];
//        while (fileInputStream.read(out) != -1) {
//            System.out.print(new String(out, StandardCharsets.UTF_8));
//        }
//
//        fileInputStream.reset();

        out = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len;
        while ((len = fileInputStream.read(out, 0, out.length)) != -1) {
            byteArrayOutputStream.write(out, 0, len);
        }
        for (byte b : byteArrayOutputStream.toByteArray()) {
            System.out.print((char) b);
        }

    }
}
