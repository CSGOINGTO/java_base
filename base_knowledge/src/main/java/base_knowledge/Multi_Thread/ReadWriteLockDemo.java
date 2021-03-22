package base_knowledge.Multi_Thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Map<String, String> map = new HashMap<>(1024);
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        final ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        final ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        ReentrantLock reentrantLock = new ReentrantLock();
        final Condition readCondition_1 = reentrantLock.newCondition();
        final Condition readCondition_2 = reentrantLock.newCondition();
        new Thread(() -> {
            readLock.lock();
//            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName() + " : start");
//            try {
//                readCondition_1.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            String hello = map.get("hello");
            if (null == hello) {
                readLock.unlock();
                writeLock.lock();
                map.put("hello", "锁降级");
                writeLock.unlock();
                readLock.lock();
                System.out.println(Thread.currentThread().getName() + " : " + map.get("hello"));
            }
            readLock.unlock();
//            reentrantLock.unlock();
        }, "read-1").start();
        new Thread(() -> {
            readLock.lock();
//            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName() + " : start");
//            try {
//                readCondition_1.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(Thread.currentThread().getName() + " : " + map.get("hello1"));
            readLock.unlock();
//            reentrantLock.unlock();
        }, "read-2").start();

        TimeUnit.SECONDS.sleep(3);

        new Thread(() -> {
            writeLock.lock();
//            reentrantLock.lock();
//            for (int i = 0; i < 10000000; i++) {
                map.put("hello", "write-1" );
//                map.put("" + i, "" + i);
//            }
            writeLock.unlock();
//            readCondition_1.signal();
//            reentrantLock.unlock();
            System.out.println(Thread.currentThread().getName() + " : " + "end");
        }, "write-1").start();
        new Thread(() -> {
            writeLock.lock();
//            reentrantLock.lock();
//            for (int i = 0; i < 10000000; i++) {
                map.put("hello1", "write-2");
//                map.put(" _" + i, " _" + i);
//            }
            writeLock.unlock();
//            readCondition_1.signal();
//            reentrantLock.unlock();
            System.out.println(Thread.currentThread().getName() + " : " + "end");
        }, "write-2").start();

    }
}
