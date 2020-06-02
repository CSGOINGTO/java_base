package base_knowledge.proxy;

/**
 * 被代理的学生对象
 */
public class Student implements Person {

    @Override
    public void say(String msg) {
        System.out.println("student say " + msg);
    }
}
