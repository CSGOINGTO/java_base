package design_patterns.proxy.dyncProxy;

import design_patterns.proxy.staticProxy.RealSubject;
import design_patterns.proxy.staticProxy.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        Subject subject = new RealSubject("被代理对象");
        Subject s = (Subject) Proxy.newProxyInstance(contextClassLoader, subject.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("toString")) {
                    return proxy.getClass().toString();
                }
                System.out.println("动态代理类方法执行...");
                Object invoke = method.invoke(subject, args);
                System.out.println("动态代理类方法执行完毕...");
                return invoke;
            }
        });
        s.print(s.getSubjectName());
        System.out.println(s);
    }
}
