package base_knowledge.Multi_Thread;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest implements Runnable{
    private volatile boolean goal = false;
    AtomicInteger count = new AtomicInteger(0);
    public boolean isGoal() {
        return goal;
    }

    public void setGoal(boolean goal) {
        this.goal = goal;
    }

    @Override
    public void run() {
        while (true) {
            if (isGoal()) {
                System.out.println("Goal !!!!!");
                count.incrementAndGet();
                System.out.println("当前进球数为：" + count.get());
                setGoal(false);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTest volatileTest = new VolatileTest();
        Thread thread = new Thread(volatileTest);
        thread.start();
        Thread.sleep(3000);
        volatileTest.setGoal(true);
        Thread.sleep(3000);
        volatileTest.setGoal(true);
        Thread.sleep(5000);
        volatileTest.setGoal(true);
    }
}
