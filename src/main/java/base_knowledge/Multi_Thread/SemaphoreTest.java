package base_knowledge.Multi_Thread;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Semaphore可以控制对共享资源的访问并发数
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        BoundResource boundResource = new BoundResource(3);
        for (int i = 0; i < 10; i++) {
            new UserThread(boundResource).start();
        }
    }
}

class Log {
    static void println(String msg) {
        System.out.println(Thread.currentThread().getName() + msg);
    }
}

/**
 * 资源只允许一定数量的线程访问
 */
class BoundResource {
    private final Semaphore semaphore;

    private final int permits;

    private final static Random RANDOM = new Random(1314);

    BoundResource(int permits) {
        this.semaphore = new Semaphore(permits);
        this.permits = permits;
    }

    void use() throws InterruptedException {
        semaphore.acquire();
        try {
            doUse();
        } finally {
            semaphore.release();
        }
    }

    private void doUse() throws InterruptedException {
        Log.println("Begin: used = " + (permits - semaphore.availablePermits()));
        Thread.sleep(RANDOM.nextInt(500));
        Log.println("End: used = " + (permits - semaphore.availablePermits()));
    }
}

class UserThread extends Thread {
    private final static Random RANDOM = new Random(521);
    private final BoundResource boundResource;

    UserThread(BoundResource boundResource) {
        this.boundResource = boundResource;
    }

    @Override
    public void run() {
        try {
            while (true) {
                boundResource.use();
                Thread.sleep(RANDOM.nextInt(3000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}