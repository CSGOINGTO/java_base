package base_knowledge.collection;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put(null, 2);
        map.put(null, null);
        map.put("1", null);
        for (int i = 0; i < 100; i++) {
            map.put("helloMap_" + i, i);
        }
        System.out.println(map.get("helloMap_0"));
        map.forEach((key, value) -> System.out.println(key + ": " +value));
    }
}
