package base_knowledge.blocking_queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

class Product {
    private BlockingQueue<String> blockingQueue;

    Product(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    void product() {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(i);
                    System.out.println(Thread.currentThread().getName() + "正在生产 " + i);
                    blockingQueue.put("" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
