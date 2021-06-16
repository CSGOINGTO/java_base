package base_knowledge.jvm;

import java.util.ArrayList;
import java.util.List;

public class JConsoleTest {

    private List<byte[]> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        new JConsoleTest().fillMemory();
    }

    private void addObject(int num) throws InterruptedException {
        for (int i = 0; i < num; i++) {
            byte[] b = new byte[1024 * 100];
            Thread.sleep(100);
            list.add(b);
        }
        list = null;
        System.gc();
    }

    private void fillMemory() throws InterruptedException {
        addObject(1000);
    }
}
