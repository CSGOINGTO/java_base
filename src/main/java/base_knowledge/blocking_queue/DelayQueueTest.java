package base_knowledge.blocking_queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {
    private BlockingQueue<MyDelay> delayQueue = new DelayQueue<>();

    public static void main(String[] args) {
        DelayQueueTest delayQueueTest = new DelayQueueTest();
        delayQueueTest.addElement();
        delayQueueTest.getElement();
    }

    private void addElement() {
        try {
            delayQueue.put(new MyDelay());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getElement() {
        try {
            MyDelay myDelay = delayQueue.take();
            System.out.println(myDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class MyDelay implements Delayed {

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.toSeconds(1000);
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
