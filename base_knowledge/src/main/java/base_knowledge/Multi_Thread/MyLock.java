package base_knowledge.Multi_Thread;

import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class MyLock {

    private int i = 0;

    public static void main(String[] args) throws InterruptedException {
        MyLock myLock = new MyLock();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        Semaphore semaphore = new Semaphore(2);
        for (int k = 0; k < 10; k++) {
            new Thread(() -> {
                myLock.lockShared(1);
                try {
//                    semaphore.acquire();
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 10000; j++) {
                    myLock.i++;
                }
//                semaphore.release();
                myLock.unlockShared(1);
                countDownLatch.countDown();
            }).start();
        }
//        countDownLatch.await(100, TimeUnit.SECONDS);
        countDownLatch.await();
        System.out.println(myLock.i);
    }

    MyAQS myAQS = new MyAQS(2) {
        @Override
        public boolean tryAcquire() {
            return thread.compareAndSet(null, Thread.currentThread());
        }

        @Override
        public boolean tryRelease() {
            return thread.compareAndSet(Thread.currentThread(), null);
        }

        @Override
        public int tryAcquireShared(int releases) {
            for (; ; ) {
                int count = state.get();
                if (count <= 0 || count - releases < 0) {
                    return -1;
                }
                if (state.compareAndSet(count, count - releases)) {
                    return 1;
                }
            }

        }

        @Override
        public boolean tryReleaseShared(int releases) {
            for (;;) {
                int count = state.get();
                return state.compareAndSet(count, count + releases);
            }
        }
    };

    public void lock() {

        myAQS.acquire();
    }

    public void unlock() {
        myAQS.release();
    }

    public void lockShared(int release) {
        myAQS.acquireShared(release);
    }

    public void unlockShared(int release) {
        myAQS.releaseShared(release);
    }
}

class MyAQS {

    // 拥有锁的当前线程
    volatile AtomicReference<Thread> thread = new AtomicReference<>();

    // 等待队列
    volatile LinkedBlockingQueue<Thread> blockingQueue = new LinkedBlockingQueue<>();

    volatile AtomicInteger state = new AtomicInteger(0);

    public MyAQS() {
    }

    public MyAQS(int state) {
        this.state = new AtomicInteger(state);
    }

    public void acquire() {
        Thread currentThread = Thread.currentThread();
        boolean flag = true;
        while (!tryAcquire()) {
            if (flag) {
                blockingQueue.add(currentThread);
                LockSupport.park();
                flag = false;
            }
        }
        blockingQueue.remove(currentThread);
    }

    public boolean tryAcquire() {
        throw new UnsupportedOperationException();
    }

    public void release() {
        if (tryRelease()) {
            Iterator<Thread> iterator = blockingQueue.iterator();
            if (iterator.hasNext()) {
                LockSupport.unpark(iterator.next());
            }
             /*
                在这里移除线程会出现死锁的现象。因为当前线程已经工作完毕，而它还在等待队列中，在unpark时可能会导致唤醒当前线程而没有唤醒其他线程的情况。
                因此移除线程的操作应当放在拿到锁的时候进行操作。拿到锁就相当于当前线程已经可以开始工作了，其本质上来说不算是等待的线程，这样可以保证等待队列功能的单纯性。
             */
//            blockingQueue.remove(Thread.currentThread());
        }
    }

    public boolean tryRelease() {
        throw new UnsupportedOperationException();
    }

    public void acquireShared(int releases) {
        Thread currentThread = Thread.currentThread();
        boolean flag = true;
        while (tryAcquireShared(releases) < 0) {
            if (flag) {
                blockingQueue.add(currentThread);
                LockSupport.park();
                flag = false;
            }
        }
        blockingQueue.remove(currentThread);
    }

    public int tryAcquireShared(int releases) {
        throw new UnsupportedOperationException();
    }

    public void releaseShared(int releases) {
        if (tryReleaseShared(releases)) {
            Iterator<Thread> iterator = blockingQueue.iterator();
            if (iterator.hasNext()) {
                LockSupport.unpark(iterator.next());
            }
        }
    }

    public boolean tryReleaseShared(int releases) {
        throw new UnsupportedOperationException();
    }


}
