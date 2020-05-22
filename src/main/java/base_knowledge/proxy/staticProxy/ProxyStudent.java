package base_knowledge.proxy.staticProxy;

/**
 * 静态代理学生对象
 */
public class ProxyStudent implements Person{

    Person person;

    public ProxyStudent(Person person) {
        this.person = person;
    }

    @Override
    public void say(String msg) {
        System.out.println("proxy start say...");
        person.say(msg);
        System.out.println("proxy end say...");
    }

    public static void main(String[] args) {
        ProxyStudent proxyStudent = new ProxyStudent(new Student());
        proxyStudent.say("想考100分");
    }
}
