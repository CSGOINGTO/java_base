package base_knowledge.collection;

import base_knowledge.sourcecode.java.util.HashMap;
import base_knowledge.sourcecode.java.util.Map;

public class HashMapDemo {

    public static void main(String[] args) {
        Map<MyObject, String> map = new HashMap<>();
        map.put(null, "1");
        map.put(new MyObject("sad"), null);
        for (int i = 0; i < 20; i++) {
            map.put(new MyObject(String.valueOf(i)), String.valueOf(i));
        }
        System.out.println(map.get(new MyObject(String.valueOf(1))));
    }

}


class MyObject{
    private String name;

    public MyObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyObject{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyObject myObject = (MyObject) o;
        return name.equals(myObject.name);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
