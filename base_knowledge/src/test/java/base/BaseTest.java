package base;

import org.junit.Test;

import java.text.DecimalFormat;
import java.util.Arrays;
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
        if(type != null && !"".endsWith(type) && type.endsWith("datacenter")){
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
}
