package base_knowledge.Multi_Thread;

public class ThisEscape {
    private static int num = 10;
    public ThisEscape() {
        System.out.println(Thread.currentThread().getName() + "构造函数中" + num);
        doSomething();
    }

    private void doSomething() {
        num ++;
        System.out.println(Thread.currentThread().getName() + "doSomething中" + num);
    }

    public static void main(String[] args) {
        new Thread(ThisEscape::new, "Thread-1").start();
        new Thread(ThisEscape::new, "Thread-2").start();
        new Thread(ThisEscape::new, "Thread-3").start();
    }
}

class ConstructorTest{
    private final int x;
    private int y;
    // this逸出，ConstructorTest有可能未完成初始化过程，就被使用
    private ConstructorTest(Holder holder) {
        holder.setConstructorTest(this);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        x = 1;
        y = 2;
    }

    static ConstructorTest getConstructorTest(Holder holder) {
        return new ConstructorTest(holder);
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

class Holder {
    private ConstructorTest constructorTest;

    private ConstructorTest getConstructorTest() {
        return constructorTest;
    }

    void setConstructorTest(ConstructorTest constructorTest) {
        this.constructorTest = constructorTest;
        if (constructorTest.getX() == 0) {
            throw new RuntimeException("x不能为0！");
        }
        if (constructorTest.getY() == 0) {
            throw new RuntimeException("y不能为0！");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Holder holder = new Holder();
        new Thread(() -> ConstructorTest.getConstructorTest(holder)).start();
        Thread.sleep(10000);
        while (true) {
            ConstructorTest constructorTest = holder.getConstructorTest();
            System.out.println("x = " + constructorTest.getX());
            System.out.println("y = " + constructorTest.getY());
        }
    }

}

