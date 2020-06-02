package base_knowledge.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueTest {


    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);
        Product product = new Product(blockingQueue);
        product.product();
        new Consume(blockingQueue).consume();
    }

}
