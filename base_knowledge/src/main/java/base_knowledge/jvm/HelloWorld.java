package base_knowledge.jvm;

/**
 * 1.使用javac命令将.java文件编译成.class文件
 * 2.使用javap命令将.class文件进行解析
 */
public class HelloWorld {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        int c = b / a;
        int d = 100;
        System.out.println(c + d);
    }
}
