package base_knowledge.jvm;

public class FinalizeTest {

    private static FinalizeTest finalizeTest = null;

    public static void main(String[] args) throws InterruptedException {
        finalizeTest = new FinalizeTest();
        testGc();
        testGc();
    }

    private static void testGc() throws InterruptedException {
        finalizeTest = null;
        System.gc();

//        Thread.sleep(1000);

        if (finalizeTest == null) {
            System.out.println("gcTest dead");
        } else {
            System.out.println("gcTest alive");
        }
    }

    /**
     * 只会被执行一次，并且Finalize线程优先级很低，也不保证每次执行finalize()方法时都能执行完毕
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        finalizeTest = this;
        System.out.println("finalize方法被调用...");
    }
}

