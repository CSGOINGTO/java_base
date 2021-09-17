package base_knowledge.Multi_Thread;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolParamTest {

    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(4));

    public static void main(String[] args) {
        for (int i = 0; i < 12; i++) {
            addTask();
            printThreadPoolInfo();
        }
        threadPool.setCorePoolSize(100);
        threadPool.shutdown();
        System.out.println("线程池执行完毕！");
    }

    private static void printThreadPoolInfo() {
        System.out.println("当前【核心线程】数：" + threadPool.getCorePoolSize() + ", 最大【线程】数：" + threadPool.getMaximumPoolSize() + ", 【存活线程】数：" + threadPool.getActiveCount()
        + "【阻塞队列长度】：" + threadPool.getQueue().size() + "【当前Task】数：" + threadPool.getTaskCount() );
    }

    private static void addTask() {
        threadPool.execute(() -> {
            try {
                System.out.println(new Date() + "，添加新任务...");
                TimeUnit.SECONDS.sleep(5000);
                System.out.println(new Date() + "，任务执行完成！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
