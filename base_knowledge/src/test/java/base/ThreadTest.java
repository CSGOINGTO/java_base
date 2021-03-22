package base;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadTest {

    private static final Object object = new Object();

    @Test
    public void testThread() throws InterruptedException {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        Thread[] threads =  new Thread[10];
        MyThread.enumerate(threads);
        t1.start();
        t2.start();
        System.out.println(Arrays.toString(threads));
        System.out.println(Arrays.toString(t1.getStackTrace()));
        Thread.sleep(10000);
        t1.stop();
    }

    @Test
    public void testInterrupter() {
        MyThread t1 = new MyThread();
        Thread.currentThread().interrupt();
        System.out.println(Thread.interrupted());
    }

    @Test
    public void testReadWriteLock() {
        Map<String, String> map = new HashMap<>(1024);
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        final ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        final ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        ReentrantLock reentrantLock = new ReentrantLock();
        final Condition readCondition = reentrantLock.newCondition();
        final Condition condition = reentrantLock.newCondition();
        new Thread(() -> {
            readLock.lock();
            reentrantLock.lock();
            try {
                readCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " : " + map.get("hello"));
            readLock.unlock();
            reentrantLock.unlock();
        }, "read-1").start();
        new Thread(() -> {
            readLock.lock();
            reentrantLock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " : " + map.get("hello1"));
            readLock.unlock();
            reentrantLock.unlock();
        }, "read-2").start();
        new Thread(() -> {
            writeLock.lock();
            reentrantLock.lock();
            for (int i = 0; i < 1000; i++) {
                map.put("hello", "" + i);
                map.put("" + i, "" + i);
            }
            writeLock.unlock();
            readCondition.signal();
            reentrantLock.unlock();
            System.out.println(Thread.currentThread().getName() + " : " + "end");
        }, "write-1").start();
        new Thread(() -> {
            writeLock.lock();
            reentrantLock.lock();
            for (int i = 0; i < 1000; i++) {
                map.put("hello1", "" + i);
                map.put(" _" + i, " _" + i);
            }
            writeLock.unlock();
            condition.signal();
            reentrantLock.unlock();
            System.out.println(Thread.currentThread().getName() + " : " + "end");
        }, "write-2").start();

    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("Hello..." + Thread.currentThread().getName());
                try {
                    method();
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void method() {
            System.out.println("方法...");
        }

    }
}



