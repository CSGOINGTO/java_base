package base_knowledge.base.loadorder;

public class Father {

    private String fatherName;

    private static final String Age = "20";

    /*
    如果该类为父类，那么默认的无参构造器必须存在！
     */
    Father() {
        System.out.println("我是父类无参构造器！");
    }

    Father(String fatherName) {
        System.out.println("我是父类有参构造器！");
        this.fatherName = fatherName;
    }

    /*
    静态变量和静态代码块的执行看两者的先后顺序
     */
    private static final String ADDRESS = "address";

    static {
        System.out.println("我是父类静态代码块！");
        System.out.println("父类Age： " + Age);
        System.out.println("父类Address" + ADDRESS);
    }

    {
        System.out.println("我是父类匿名代码块");
    }

    @Override
    public String toString() {
        return "Father{" +
                "name='" + fatherName + '\'' + ", age='" + Age + '\'' +
                '}';
    }
}
