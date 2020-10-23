package base_knowledge.Multi_Thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new MyThread(countDownLatch).start();
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + ": countDown...");
            countDownLatch.countDown();
        }
    }
}

class MyThread extends Thread {

    private final CountDownLatch countDownLatch;

    public MyThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + ": await start...");
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName() + ": await over...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
