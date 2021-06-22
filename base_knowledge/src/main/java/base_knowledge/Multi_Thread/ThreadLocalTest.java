package base_knowledge.Multi_Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest {

    private static AtomicInteger j = new AtomicInteger(1);

    public static void main(String[] args) {

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(() -> {
                ThreadLocal<String> threadLocal = new ThreadLocal<>();
                threadLocal.set(Thread.currentThread().getName());
                // 会将上面设置的值覆盖掉
                threadLocal.set("第i个线程：" + j.getAndIncrement());
                ThreadLocal<Object> threadLocal1 = new ThreadLocal<>();
                // 在threadLocalMap中的Entry数组中再放一个Entry
                threadLocal1.set(new ThreadLocalTest());
                System.out.println(threadLocal.get());
                System.out.println(threadLocal1.get());
            });
        }
    }

}
