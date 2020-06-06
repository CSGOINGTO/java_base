package base_knowledge.Multi_Thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/*
    一、线程池的数量需要根据任务的类型设定：
    1. 对于计算型任务（占用CPU资源），线程池中核心线程的数量最好在CPU的核数1-2倍；
    2. 对于IO型任务（不占用CPU资源），线程池中核心线程的数量需要根据具体的业务而设定。
    二、线程池最好自己设置，并且给定ThreadFactory，方便排查问题
*/
public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        fixedThreadPoolTest();
//        cacheThreadPoolTest();
//        singleThreadPoolTest();
        scheduledThreadPoolTest();
    }

    /**
     * 定时线程池
     */
    private static void scheduledThreadPoolTest() {
        final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        // 延时任务，单次执行
        scheduledThreadPool.schedule(() -> {
            System.out.println(Thread.currentThread().getName());
        }, 5, TimeUnit.SECONDS);

        // 定时任务，任务执行时间比间隔时间长的话，需要等到任务执行完毕，但是下个任务会立即执行
//        scheduledThreadPool.scheduleAtFixedRate(() -> {
//            System.out.println("startTime:" + new Date());
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("endTime" + new Date());
//        }, 5, 1, TimeUnit.SECONDS);

        // 定时任务，任务执行时间比间隔时间长的话，需要等到任务执行完毕之后，再按照间隔时间继续执行
        scheduledThreadPool.scheduleWithFixedDelay(() -> {
            System.out.println("startTime" + new Date());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("endTime" + new Date());
        }, 5, 1, TimeUnit.SECONDS);
    }


    /**
     * 单核心线程池
     */
    private static void singleThreadPoolTest() throws InterruptedException {
        final ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        final List<Future<String>> futureList = singleThreadPool.invokeAll(getCallables());
        final long startTime = System.currentTimeMillis();
        futureList.forEach((future -> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }));
        singleThreadPool.shutdown();
        if (singleThreadPool.isShutdown()) {
            final long endTime = System.currentTimeMillis();
            System.out.println("执行时间：" + (endTime - startTime) / 1000 + "s");
        }

    }

    /**
     * 每次来一个新任务，如果没有空闲线程，就会开启一个新的线程
     */
    private static void cacheThreadPoolTest() throws InterruptedException {
        final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        final List<Future<String>> futureList = cachedThreadPool.invokeAll(getCallables());
        final long startTime = System.currentTimeMillis();
        futureList.forEach((future) -> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        cachedThreadPool.shutdown();
        if (cachedThreadPool.isShutdown()) {
            final long endTime = System.currentTimeMillis();
            System.out.println("执行时间：" + (endTime - startTime) / 1000 + "s");
        }
    }

    /**
     * 核心线程数和最大线程数一样的线程池
     */
    private static void fixedThreadPoolTest() throws InterruptedException, ExecutionException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool((int) (Runtime.getRuntime().availableProcessors() * 1.5));
        List<Callable<String>> threads = getCallables();
        final long startTime = System.currentTimeMillis();
        List<Future<String>> futures = fixedThreadPool.invokeAll(threads);
        for (Future<String> f : futures) {
            System.out.println(f.get());
        }
        fixedThreadPool.shutdown();
        if (fixedThreadPool.isShutdown()) {
            final long endTime = System.currentTimeMillis();
            System.out.println("执行时间：" + (endTime - startTime) / 1000 + "s");
        }
    }

    private static List<Callable<String>> getCallables() {
        List<Callable<String>> threads = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            threads.add(new MyCallable());
        }
        return threads;
    }


}

class MyCallable implements Callable<String> {

    Random random = new Random();

    @Override
    public String call() throws Exception {
        int count = 0;
        for (int i = 0; i < 1000; i++) {
            count += random.nextInt();
        }
        System.out.println(Thread.currentThread().getName() + "线程执行执行结果：" + count);
        return Thread.currentThread().getName() + "线程执行执行结果：" + count;
    }
}
