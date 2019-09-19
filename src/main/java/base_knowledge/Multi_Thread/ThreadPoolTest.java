package base_knowledge.Multi_Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        List<Callable<String>> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(() -> Thread.currentThread().getName());
        }
        List<Future<String>> futures = fixedThreadPool.invokeAll(threads);
        for (Future<String> f : futures) {
            System.out.println(f.get());
        }
        fixedThreadPool.shutdown();
    }
}
