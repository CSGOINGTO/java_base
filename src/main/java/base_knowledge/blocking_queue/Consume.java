package base_knowledge.blocking_queue;

import java.util.concurrent.BlockingQueue;

class Consume {
    private BlockingQueue<String> blockingQueue;

    Consume(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    void consume() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "正在取" + blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
