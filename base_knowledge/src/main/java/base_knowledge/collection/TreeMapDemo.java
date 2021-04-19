package base_knowledge.collection;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class TreeMapDemo {
    public static void main(String[] args) {
        Random random = new Random();
        Map<String, Integer> map = new TreeMap<>();
        // map.put(null, 1); NullPointException
        map.put("null", null);
        for (int i = 0; i < 20; i++) {
            int nextInt = random.nextInt(100);
            System.out.println(nextInt);
            map.put("HelloTreeMap_" + nextInt, nextInt);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
