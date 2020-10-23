package base;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    @Test
    public void testReentrantLock() {
        Lock lock = new ReentrantLock();

        new Thread(() -> {
           try {
               lock.lock();
               System.out.println(Thread.currentThread().getName() + ":start");
               Thread.sleep(5000);
               System.out.println(Thread.currentThread().getName() + ":end");
           } catch (InterruptedException e) {
               e.printStackTrace();
           } finally {
               lock.unlock();
           }
        }, "Thread-1").start();

        new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + ":start");
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + ":end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "Thread-2").start();

        new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + ":start");
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + ":end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "Thread-3").start();
    }

}
