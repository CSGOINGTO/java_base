package base_knowledge.jvm;

public class RunTimePoolTest {
    public static void main(String[] args) {

        // intern返回首次放入字符串池中的引用
        String str1 = new StringBuilder().append("Hello").append(" World!").toString();
        // 首次放入，返回true
        System.out.println(str1.intern() == str1); // true

        String str2 = new StringBuilder().append("Ja").append("va").toString();
        // 返回的都是首次放入字符串池中的引用，返回true
        System.out.println(str2.intern() == str2.intern()); // true

        // 因为Java字符串在启动时，被另外一个内置类加载，因此str2.intern()返回的是首次的引用，str2是新生成的一个引用，返回false
        System.out.println(str2.intern() == str2); // false
    }
}
