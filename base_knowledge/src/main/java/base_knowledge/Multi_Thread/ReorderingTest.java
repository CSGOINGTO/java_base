package base_knowledge.Multi_Thread;

/**
 * 指令重排序
 */
public class ReorderingTest {
    private boolean flag = true;
    public static void main(String[] args) {
        final ReorderingTest reorderingTest = new ReorderingTest();
        new Thread(() -> {
            int i = 0;
            while (reorderingTest.flag) {// if(reorderingTest.flag) {
                                         //     while(true) {
                                         //         i++;
                                         //     }
                                         // }
                i++;
            }
            System.out.println(i);
        }).start();
        new Thread(() -> {
            reorderingTest.flag = false;
            System.out.println("修改完毕！");
        }).start();
    }
}
