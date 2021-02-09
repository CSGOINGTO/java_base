package base_knowledge.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectClass {

    public static void main(String[] args) {
        reflectNewInstance();
        reflectPrivateConstructor();
        reflectPrivateMethod();
        reflectPrivateField();
    }

    private static final String basePackage = "base_knowledge.reflect.Book";

    // 创建对象
    public static void reflectNewInstance() {
        try {
            Class<?> bookClass = Class.forName(basePackage);
            Book book = (Book) bookClass.newInstance();
            book.setName("放假啦！");
            book.setAuthor("China");
            System.out.println(book);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void reflectPrivateConstructor() {
        Class<?> bookClass = null;
        try {
            bookClass = Class.forName(basePackage);
            Constructor<?>[] constructors = bookClass.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                System.out.println(constructor.getParameters().length);
                if (constructor.getParameters().length == 2) {
                    constructor.setAccessible(true);

                    Book book = (Book) constructor.newInstance("name", "author");
                    System.out.println(book);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void reflectPrivateMethod() {
        try {
            Class<?> bookClass = Class.forName(basePackage);
            Method declaredMethod = bookClass.getDeclaredMethod("declaredMethod", int.class);
            declaredMethod.setAccessible(true);
            Book book = (Book) bookClass.newInstance();
            String str = (String) declaredMethod.invoke(book, 0);
            System.out.println(str);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void reflectPrivateField() {
        try {
            Class<?> bookClass = Class.forName(basePackage);
            Field tag = bookClass.getDeclaredField("TAG");
            tag.setAccessible(true);
            String tagStr = (String) tag.get(bookClass);
            System.out.println(tagStr);
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
