package leetcode.mulitThread.printinOrder_1114;

public class Foo {
    public Foo() {
    }

    private volatile int count = 0;

    public void one() throws InterruptedException {
        while (true) {
            if (count % 3 == 0) {
                System.out.println("one");
                this.count++;
            }
        }
    }

    public void two() throws InterruptedException {
        while (true) {
            if (count % 3 == 1) {
                System.out.println("two");
                this.count++;
            }
        }
    }

    public void three() throws InterruptedException {
        while (true) {
            if (count % 3 == 2) {
                System.out.println("three");
                this.count++;
            }
        }
    }

    public static void main(String[] args) {
        Foo foo = new Foo();
        new Thread(() -> {
            try {
                foo.one();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.two();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.three();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
    }

    public void third(Runnable printThird) throws InterruptedException {
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
