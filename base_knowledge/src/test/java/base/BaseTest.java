package base;

import org.junit.Test;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BaseTest {
    @Test
    public void testDouble() {
        DecimalFormat df = new DecimalFormat("#0.00");
        System.out.println(df.format(Double.parseDouble("1063004405.7599999905") / (1024 * 1024 * 1024)));
        System.out.println(df.format(0.456));
        System.out.println(Math.round(0.456));
        System.out.println(df.format(0.566));
        System.out.println(Math.round(0.566));
        System.out.println(df.format(0.666));
        System.out.println(Math.round(0.666));

        String type = "datacenter";
        if (type != null && !"".endsWith(type) && type.endsWith("datacenter")) {
            System.out.println(type);
        }
    }

    @Test
    public void testGetClass() {
        Number n = 0;
        Class<? extends Number> c = n.getClass();
        System.out.println(c);
    }

    /**
     * LongCache会预先缓存-128–127范围内的数，通过缓存频繁请求的值代来更好的空间和时间性能。
     * ==是比较的地址
     * equals先对类型进行比较，然后进行值的比较
     */
    @Test
    public void testLong() {
        Long l1 = (long) 10;
        Long l2 = (long) 10;
        System.out.println(l1 == l2);
        System.out.println(l1.equals(l2));
        Long l3 = 128L;
        Long l4 = 128L;
        System.out.println(l3 == l4);
        System.out.println(l3.equals(l4));
        System.out.println(l3.equals(128));
    }

    @Test
    public void testAtomic() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        atomicInteger.addAndGet(10);
        System.out.println(atomicInteger.get());
    }

    @Test
    public void testString() {
        String s = "_xxx_12.23.23";
        System.out.println(Arrays.toString(s.split("_")));
    }

    @Test
    public void testException() throws InterruptedException {
        try {
            System.out.println("xxxxxx");
            Thread.sleep(10000000);
        } catch (Error error) {
            System.out.println("发生了Error");
        } finally {
            System.out.println("执行完毕！！！！");
        }

    }

    @Test
    public void testHashMap(){
        try {
            Map<Object, Object> listStringMap = new HashMap<>();
            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            list1.add("list1");
            list2.add("list2");
            listStringMap.put(list1, "list1");
            listStringMap.put(list2, "list2");
            listStringMap.put(list1, listStringMap);
            // 将自己作为key是不行的，但是可以作为value，但是不建议
//            listStringMap.put(listStringMap, listStringMap);
            System.out.println(listStringMap.get(listStringMap));
            System.out.println(listStringMap.get(list1));
            System.out.println(listStringMap.get(list2));
            list1.clear();
            list1.add("list2");
            System.out.println(listStringMap.get(list1));
            System.out.println(listStringMap.get(list2));
        } finally {
            System.out.println("结束调用...");
        }

    }

    @Test
    public void testListSize() {
        int size = 10;
        for (int i = 0; i < 100; i++) {
            System.out.println(size >> 1);
            size += size >> 1;
        }
    }
}
