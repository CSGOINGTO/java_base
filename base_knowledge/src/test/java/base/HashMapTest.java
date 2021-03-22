package base;

import base_knowledge.sourcecode.java.util.HashMap;
import base_knowledge.sourcecode.java.util.Map;
import org.junit.Test;

public class HashMapTest {

    @Test
    public void testHashMap() {
        Map<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < 18000; i++) {
            final String x = hashMap.put("" + i, "" + i);
            if (x != null)
                System.out.println(x);
        }
    }
}
