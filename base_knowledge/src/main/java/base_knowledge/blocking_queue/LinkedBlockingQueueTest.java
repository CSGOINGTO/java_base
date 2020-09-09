package base_knowledge.blocking_queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class LinkedBlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue<String> linkedBlockingQueue = new LinkedBlockingDeque<>();
        new Product(linkedBlockingQueue).product();
        new Consume(linkedBlockingQueue).consume();
    }


}
