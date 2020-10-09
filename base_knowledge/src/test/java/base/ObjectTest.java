package base;

import org.junit.Test;

public class ObjectTest {

    @Test
    public void testObject() {
        Number n = 0;
        Class<? extends Number> c = n.getClass();
        // 编译通不过
//        Class<Number> c1 = n.getClass();
    }

}
