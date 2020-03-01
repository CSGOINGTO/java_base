package base_knowledge.Multi_Thread;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {
    private ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors(), ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
    private List<Integer> nums = Lists.newArrayList();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinTest forkJoinTest = new ForkJoinTest();
        for (int i = 0; i <= 100000000; i++) forkJoinTest.nums.add(i);
        MyTask myTask = new MyTask(forkJoinTest.nums, 0, forkJoinTest.nums.size() - 1);
        ForkJoinTask<Integer> submit = forkJoinTest.forkJoinPool.submit(myTask);
        System.out.println(submit.get());
        forkJoinTest.forkJoinPool.shutdown();
//        int j = 0;
//        for (int i = 0; i <= 100000000; i++) j+= i;
//        System.out.println(j);
    }

}

class MyTask extends RecursiveTask<Integer> {

    private List<Integer> nums;

    private int start;

    private int end;

    MyTask(List<Integer> nums, int start, int end) {
        this.nums = nums;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int res = 0;
        int mid = (start + end) / 2;
        if (start + 10000 >= mid) {
            for (int i = start; i <= end; i++) {
                res += i;
            }
            return res;
        } else {
            MyTask myTask = new MyTask(nums, start, mid);
            myTask.fork();
            MyTask myTask1 = new MyTask(nums, mid + 1, end);
            myTask1.fork();
            res += myTask.join();
            res += myTask1.join();
            return res;
        }
    }
}
