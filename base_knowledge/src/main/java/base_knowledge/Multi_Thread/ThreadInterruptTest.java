package base_knowledge.Multi_Thread;

public class ThreadInterruptTest {
    public static void main(String[] args) {
        final Object lock = new Object();
        // wait
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println(Thread.currentThread().getName() + "running...");
                } catch (InterruptedException e) {
                    System.out.println("中断状态：" + Thread.currentThread().isInterrupted());
                    System.out.println(Thread.currentThread().getState());
                    System.out.println(Thread.currentThread().getName() + "interrupt！");
                }
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "running...");
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                System.out.println("中断状态：" + Thread.currentThread().isInterrupted());
                System.out.println(Thread.currentThread().getState());
                System.out.println(Thread.currentThread().getName() + "interrupt！");
            }
        }, "t2");

        Thread t3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "join...");
            try {
                Thread.currentThread().join();
            } catch (InterruptedException e) {
                System.out.println("中断状态：" + Thread.currentThread().isInterrupted());
                System.out.println(Thread.currentThread().getState());
                System.out.println(Thread.currentThread().getName() + "interrupt！");
            }
        }, "t3");
        Thread t4 = new Thread(() -> {
            Thread thread = Thread.currentThread();
            String threadName = thread.getName();
            thread.interrupt();
            System.out.println(threadName + thread.getState());
            System.out.println(threadName + thread.isInterrupted());
            boolean interrupted = Thread.interrupted();
            System.out.println(threadName + interrupted);
            System.out.println(threadName + thread.isInterrupted());
        }, "t4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.interrupt();
        t2.interrupt();
        t3.interrupt();
        System.out.println("over...");
    }
}
