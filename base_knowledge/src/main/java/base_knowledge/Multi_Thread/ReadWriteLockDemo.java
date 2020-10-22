package base_knowledge.Multi_Thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(1024);
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        final ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        final ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        ReentrantLock reentrantLock = new ReentrantLock();
        final Condition readCondition_1 = reentrantLock.newCondition();
        final Condition readCondition_2 = reentrantLock.newCondition();
        new Thread(() -> {
//            readLock.lock();
            reentrantLock.lock();
            try {
                readCondition_1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " : " + map.get("hello"));
//            readLock.unlock();
            reentrantLock.unlock();
        }, "read-1").start();
        new Thread(() -> {
//            readLock.lock();
            reentrantLock.lock();
            try {
                readCondition_1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " : " + map.get("hello1"));
//            readLock.unlock();
            reentrantLock.unlock();
        }, "read-2").start();
        new Thread(() -> {
//            writeLock.lock();
            reentrantLock.lock();
            for (int i = 0; i < 1000; i++) {
                map.put("hello", "" + i);
                map.put("" + i, "" + i);
            }
//            writeLock.unlock();
            readCondition_1.signal();
            reentrantLock.unlock();
            System.out.println(Thread.currentThread().getName() + " : " + "end");
        }, "write-1").start();
        new Thread(() -> {
//            writeLock.lock();
            reentrantLock.lock();
            for (int i = 0; i < 1000; i++) {
                map.put("hello1", "" + i);
                map.put(" _" + i, " _" + i);
            }
//            writeLock.unlock();
            readCondition_1.signal();
            reentrantLock.unlock();
            System.out.println(Thread.currentThread().getName() + " : " + "end");
        }, "write-2").start();
    }
}
