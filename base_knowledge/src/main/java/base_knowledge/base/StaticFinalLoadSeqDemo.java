package base_knowledge.base;

public class StaticFinalLoadSeqDemo {

    private static HelpClass staticHelpClass = new HelpClass("static - helpClass");

    static {
        System.out.println("static code 1");
    }

    static {
        System.out.println("static code 2");
    }

    {
        System.out.println("code 1");
    }

    public StaticFinalLoadSeqDemo(HelpClass helpClass) {
        System.out.println("具有HelpClass参数的构造函数执行");
        this.helpClass = helpClass;
    }

    public StaticFinalLoadSeqDemo() {
        System.out.println("无参的构造函数执行");
    }

    private HelpClass helpClass = new HelpClass("helpClass");

    private final HelpClass finalHelpClass = new HelpClass("final - helpClass");

    private static final HelpClass staticFinalHelpClass = new HelpClass("static-final - helpClass");

    private static void method() {
        System.out.println("static method...");
    }

    public final void method1() {
        System.out.println("final method...");
    }

    public void method2() {
        System.out.println("普通方法...");
    }

    public static void main(String[] args) {
        System.out.println("main方法开始！");
        // static方法执行，只会触发static修饰的变量和static代码块的执行
//        StaticFinalLoadSeqDemo.method();
        // 触发所有的初始化操作，并且构造器在所有初始化的最后执行
//        new StaticFinalLoadSeqDemo();
        System.out.println("main方法执行结束！");
    }

    {
        System.out.println("code 2");
    }
}

class HelpClass{
    private String name;

    static {
        System.out.println("HelpClass static code");
    }

    public HelpClass(String name) {
        System.out.println("初始化Name: " + name);
        this.name = name;
    }

    @Override
    public String toString() {
        return "HelpClass{" +
                "name='" + name + '\'' +
                '}';
    }
}
