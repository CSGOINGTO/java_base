package base_knowledge.proxy.dynamicProxy;

import base_knowledge.proxy.Person;
import base_knowledge.proxy.Student;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 自定义InvocationHandler
 */
public class MyInvocationHandler implements InvocationHandler {

    private final Object object;

    public MyInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("My invocationHandler invoke begin...");
        System.out.println("proxy: " + proxy.getClass().getName());
        System.out.println("method: " + method.getName());
        if (args != null) {
            for (Object o : args) {
                System.out.println("arg: " + o);
            }
        }
        if (method.getName().equals("toString")) {
            return proxy.getClass().toString();
        } else if (method.getName().equals("hashCode")) {
            return proxy.getClass().hashCode();
        } else {
            method.invoke(object, args);
        }
        System.out.println("My invocationHandler invoke end...");
        return proxy.getClass();
    }

    public static void main(String[] args) throws Throwable {
        Student student = new Student();
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);
        // 获取代理对象
        final Person person = (Person) Proxy.newProxyInstance(classLoader, student.getClass().getInterfaces(), new MyInvocationHandler(student));
        System.out.println("person: " + person);
        person.say("我想考100！");


        final Object say = new MyInvocationHandler(student).invoke(student, student.getClass().getMethod("say", String.class), new Object[]{"我想要100"});
        System.out.println(say);
    }
}
