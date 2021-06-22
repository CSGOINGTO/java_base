package base_knowledge.Multi_Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws Exception {

        FutureTask<String> target = new FutureTask<>(new MyCallableImpl());
        Thread thread = new Thread(target);
        thread.start();
        System.out.println(target.get());
    }
}


class MyCallableImpl implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "hello";
    }

}
