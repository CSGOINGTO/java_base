package base_knowledge.Multi_Thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        int num = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num);
        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                try {
                    String name = Thread.currentThread().getName();
                    System.out.println(name + "开始！");
                    cyclicBarrier.await();
                    System.out.println(name + "工作！");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
