package base_knowledge.Multi_Thread;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class ListeningExecutorServiceDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        ListenableFuture<String> future = listeningExecutorService.submit(() -> Thread.currentThread().getName());
        Futures.addCallback(future, new MyFutureCallback(), listeningExecutorService);
//        future.addListener(() -> {
//            try {
//                System.out.println("回调开始---");
//                System.out.println(future.get());
//                System.out.println("回调结束---");
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        }, Executors.newSingleThreadExecutor());
//        Thread.sleep(5000);
        listeningExecutorService.shutdown();
    }
}
class MyFutureCallback implements FutureCallback<String> {

    @Override
    public void onSuccess(@Nullable String s) {
        System.out.println("回调成功！" + s);
    }

    @Override
    public void onFailure(Throwable throwable) {
        System.out.println("回调失败！");
    }
}
