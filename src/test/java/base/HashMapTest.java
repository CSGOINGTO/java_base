package base;

import org.junit.Test;

public class HashMapTest {

    @Test
    public void testHashMap() {
        long start = System.currentTimeMillis();
        int a = 10;
        for (long i = 0; i < 10000000000L; i++) {
            a += 1;
        }
        long end = System.currentTimeMillis();
        System.out.println(a);
        System.out.println(end - start);
    }
}
