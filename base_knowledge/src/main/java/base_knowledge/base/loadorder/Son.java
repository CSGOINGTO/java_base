package base_knowledge.base.loadorder;

public class Son extends Father{

    private String sonName = "sonName";

    /*
    子类构造器会默认调用父类的无参构造器super()
     */
    Son() {
        System.out.println("我是子类无参构造器！");
    }

    /*
    构造器的加载顺序在静态代码、匿名代码块和全局变量之后，并且执行时会默认调用父类的无参构造器
     */
    Son(String sonName) {
        System.out.println("我是子类有参构造器！");
        this.sonName = sonName;
    }

    /*
    匿名代码块和全局变量处于同一级加载顺序，当类中的静态代码执行完毕之后，按照先后顺序执行匿名代码块和全局变量
     */
    {
        System.out.println("我是子类匿名代码块！");
        System.out.println("我是子类匿名代码块中的sonName：" + sonName);
    }

    static {
        System.out.println("我是子类静态代码块！");
    }

    @Override
    public String toString() {
        return "Son{" +
                "sonName='" + sonName + '\'' +
                '}';
    }
}
