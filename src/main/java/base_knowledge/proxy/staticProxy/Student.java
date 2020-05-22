package base_knowledge.proxy.staticProxy;

public class Student implements Person{

    @Override
    public void say(String msg) {
        System.out.println("student say " + msg);
    }
}
