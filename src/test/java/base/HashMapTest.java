package base;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

    @Test
    public void testHashMap() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,1);
        map.put(1,2);
        map.put(1,3);
        System.out.println(map.size());
        boolean b = map.containsValue(1);
        System.out.println(b);
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
