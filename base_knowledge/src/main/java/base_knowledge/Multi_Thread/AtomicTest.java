package base_knowledge.Multi_Thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

    private AtomicInteger integer = new AtomicInteger();

    private void increase() {
        integer.getAndIncrement();
    }

    public static void main(String[] args) {
        AtomicTest atomicTest = new AtomicTest();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                atomicTest.increase();
            }
        }, "t1").start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                atomicTest.increase();
            }
        }, "t2").start();
    }
}
