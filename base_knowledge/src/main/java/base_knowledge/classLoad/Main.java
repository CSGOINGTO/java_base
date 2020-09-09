package base_knowledge.classLoad;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException {
        java.lang.Object object = new java.lang.Object();
        System.out.println(object.getClass().getClassLoader());
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL("file:\\E:\\git\\java_idea\\java_base\\base_knowledge\\src\\main\\java\\base_knowledge\\classLoad")});
        // 加载MyClass类
        final Class<?> aClass = urlClassLoader.loadClass(MyClass.class.getName());
        // 获取MyClass的一个实例
        final MyClass myClass = (MyClass) aClass.newInstance();
        /*
         * 双亲委派模型：
         * 1.防止自定义的类覆盖JDK中定义的类（增加安全性）
         * 2.防止类重复加载（只有包名+类名+同一个类加载器加载的类，才被认为是相同的类）
         */
        System.out.println(myClass.getClass().getClassLoader());
        System.out.println(myClass.getClass().getClassLoader().getParent());
        System.out.println(myClass.getClass().getClassLoader().getParent().getParent());
        myClass.printClassName();

        final Class<?> aClass1 = urlClassLoader.loadClass(Object.class.getName());
        final Object o = (Object) aClass1.newInstance();
        o.say("world!");
        System.out.println(o.getClass().getClassLoader());
    }
}
