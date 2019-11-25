package leetcode.mulitThread.printinOrder_1114;

/**
 * 三个线程依次打印ABC
 */
public class PrintABC {
    public static void main(String[] args) throws InterruptedException {
//        testWaitNotifyAll();

        testVolatile();
    }

    private static void testVolatile() {
        new Thread(new PrintThread_volatile("A", 0)).start();
        new Thread(new PrintThread_volatile("B", 1)).start();
        new Thread(new PrintThread_volatile("C", 2)).start();
    }

    private static void testWaitNotifyAll() throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        new PrintThread_wait_notifyAll("A", c, a).start();
        Thread.sleep(1000);
        new PrintThread_wait_notifyAll("B", a, b).start();
        Thread.sleep(1000);
        new PrintThread_wait_notifyAll("C", b, c).start();
    }
}

/**
 * 使用wait、notify/notifyAll来完成线程之间的通信
 */
class PrintThread_wait_notifyAll extends Thread {
    private final String name;
    private final Object prev;
    private final Object curr;

    PrintThread_wait_notifyAll(String name, Object prv, Object curr) {
        this.name = name;
        this.prev = prv;
        this.curr = curr;
    }

    @Override
    public void run() {
        try {
            printABC();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printABC() throws InterruptedException {
        int count = 10;
        while (count > 0) {
            synchronized (prev) {
                synchronized (curr) {
                    System.out.println(this.name);
                    count--;
                    // 唤醒处于等待该对象锁的线程
                    curr.notifyAll();
                }
                // 最后一次直接唤醒等待该对象锁的线程
                if (count == 0) {
                    prev.notifyAll();
                } else {
                    // 将该线程进入之前对象锁的等待队列中（比如A等待C唤醒，B等待A唤醒，C等待B唤醒）
                    prev.wait();
                }
            }
        }
    }
}

class PrintThread_volatile implements Runnable {

    private String name;

    private volatile static Integer count = 0;

    private int address;

    public PrintThread_volatile(String name, int address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; ) {
            if (count % 3 == address) {
                System.out.print(this.name);
                count++;
                i++;
            }
        }
    }
}