package base_knowledge.generic;

import java.util.ArrayList;
import java.util.List;

public class GenericMethod {
    /**
     * 泛型类，声明该类的类型为Generic中的T类型
     * @param <T>
     */
    public class Generic<T> {
        private T key;

        /**
         * 泛型方法，声明该方法接受类型为K（也可以为T，不过这个这T和泛型方法中声明的T不同，为了不混淆，尽量不要这样声明）
         * @param key
         * @param <K>
         */
        public <K> void setKey(K key) {
            this.key = (T) key;
        }

        /**
         * 泛型方法与可变参数结合
         * @param args
         * @param <U>
         */
        public <U> void printMsg(U... args) {
            for (U u : args) {
                System.out.println(u);
            }
        }

        public T getKey() {
            return key;
        }
    }

    /**
     * 静态泛型方法：对于一个静态static的方法，无法访问泛型类型的参数。因此如果要使用静态泛型方法，就必须使其成为泛型方法。
     * @param k
     * @param <K>
     */
    public static <K> void testStaticMethod(K k) {
        System.out.println("静态泛型方法");
    }

    public static void main(String[] args) {
        Generic<Person> man = new GenericMethod().new Generic<>();
        man.setKey(new Person("男"));
        System.out.println(man.getKey().getSex());
        man.setKey(new Student("男", "xxx"));
        System.out.println(man.getKey().getSex());
        man.setKey(new Computer("安徽"));
        // 报错，Computer不能转成Person类型
//        System.out.println(man.getKey().getSex());
        // 测试泛型方法与可变参数
        man.printMsg("xxx", "wwww", 1, man);

        int[] a = new int[10];
        List<?>[] ls1 = new ArrayList<?>[10];
        List<String>[] ls2 = new ArrayList[10];
        /**
         * !!!!不能创建一个确切的泛型类型的数组
         */
//        List<String>[] ls = new ArrayList<String>[10];
        /**
         * 采用通配符的方式是被允许的:数组的类型不可以是类型变量，除非是采用通配符的方式，因为对于通配符的方式，最后取出数据是要做显式的类型转换的。
         */
        List<?>[] lsa = new List<?>[10]; // OK, array of unbounded wildcard type.
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        oa[1] = li; // Correct.
        Integer i = (Integer) lsa[1].get(0); // OK
    }
}

class Person {
    String sex;

    public Person(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
}

class Student extends Person {

    String school;

    public Student(String sex, String school) {
        super(sex);
        this.school = school;
    }

    public String getSchool() {
        return school;
    }
}

class Computer {
    String productArea;

    public Computer(String productArea) {
        this.productArea = productArea;
    }

    public String getProductArea() {
        return productArea;
    }
}


