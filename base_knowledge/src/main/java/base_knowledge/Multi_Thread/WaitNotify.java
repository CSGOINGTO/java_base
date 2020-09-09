package base_knowledge.Multi_Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class WaitNotify {
    private volatile List<Integer> list = new ArrayList<>();
    private AtomicInteger i = new AtomicInteger(0);
    private synchronized void put() {
        while (list.size() == 10) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("开始生产！");
        list.add(i.getAndIncrement());
        System.out.println(list);
        this.notifyAll();
    }

    private synchronized void get() {
        while (list.size() <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("开始消费" + list.get(list.size() - 1));
        list.remove(list.size() - 1);
        this.notifyAll();
    }

    public static void main(String[] args) {
        WaitNotify waitNotify = new WaitNotify();
        for (int i = 0; i < 10; i++) {
            new Thread(waitNotify::put).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(waitNotify::get).start();
        }
    }
}
