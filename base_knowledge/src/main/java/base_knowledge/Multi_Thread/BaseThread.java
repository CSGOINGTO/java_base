package base_knowledge.Multi_Thread;

public class BaseThread extends Thread {
    private static boolean ready;
    private static int number;

    public static void main(String[] args) {
        new Thread(()->{
            while (!ready) {
                ThreadLocal<BaseThread> baseThreadThreadLocal = new ThreadLocal<>();
                baseThreadThreadLocal.set(new BaseThread());
                BaseThread baseThread = baseThreadThreadLocal.get();
                System.out.println(baseThread);
                System.out.println("我在循环中");
            }
            System.out.println(number);
        }).start();
        number = 42;
        ready = false;
    }
}
