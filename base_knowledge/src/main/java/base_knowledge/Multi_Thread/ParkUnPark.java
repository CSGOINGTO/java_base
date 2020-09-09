package base_knowledge.Multi_Thread;

import java.util.concurrent.locks.LockSupport;

public class ParkUnPark {

    private static Object object;

    public static void main(String[] args) throws InterruptedException {
//        ParkUnPark.parkUnParkTest1();
        ParkUnPark.parkUnParkDeadlockTest();
    }

    private static void parkUnParkTest() throws InterruptedException {
        final Thread thread = new Thread(() -> {
            while (object == null) {
                System.out.println("等待中。。。");
                LockSupport.park();
            }
            System.out.println("拿到Object： " + object);
        });
        thread.start();
        Thread.sleep(10000);
        object = new Object();
        LockSupport.unpark(thread);
        System.out.println("生产Object完毕！");
    }

    /**
     * unpark()方法在park()方法执行之前被执行，也可以生效
     */
    private static void parkUnParkTest1() throws InterruptedException {
        final Thread thread = new Thread(() -> {
            while (object == null) {
                final String threadName = Thread.currentThread().getName();
                System.out.println(threadName + "进入了睡眠。。。");
                try {
                    Thread.sleep(2000);
                    System.out.println(threadName + "睡眠结束。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("锁定中。。。");
                synchronized (ParkUnPark.class) {
                    LockSupport.park();
                }
                System.out.println("拿到Object： " + object);
            }
        });
        thread.start();
        object = new Object();
        synchronized (ParkUnPark.class) {
            System.out.println("解锁中。。。");
            // 先于park()执行，也可以解锁被park()锁定的线程
            LockSupport.unpark(thread);
        }
        System.out.println("生产Object完毕！");
    }

    /**
     * park()不会释放锁，unpark()拿不到锁，因此产生死锁
     */
    private static void parkUnParkDeadlockTest() throws InterruptedException {
        final Thread thread = new Thread(() -> {
            synchronized (ParkUnPark.class) {
                while (object == null) {
                    final String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + "进入了睡眠。。。");
                    try {
                        Thread.sleep(1000);
                        System.out.println(threadName + "睡眠结束。。。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("锁定中。。。");
                    // 进入锁定，但是不会释放锁资源
                    LockSupport.park();
                }
                System.out.println("拿到Object： " + object);
            }
        });
        thread.start();
        final String mainThreadName = Thread.currentThread().getName();
        System.out.println(mainThreadName + "进入了睡眠。。。");
        Thread.sleep(2000);
        System.out.println(mainThreadName + "睡眠结束。。。");
        object = new Object();
        // 拿不到锁资源，产生了死锁
        synchronized (ParkUnPark.class) {
            System.out.println("解锁中。。。");
            LockSupport.unpark(thread);
        }
        System.out.println("生产Object完毕！");
    }
}
