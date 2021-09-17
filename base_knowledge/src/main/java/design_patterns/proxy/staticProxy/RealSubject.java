package design_patterns.proxy.staticProxy;

public class RealSubject implements Subject {

    private String name;

    public RealSubject(String name) {
        this.name = name;
        System.out.println("RealSubject name: " + name + "初始化完成");
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSubjectName() {
        return name;
    }

    @Override
    public void print(String string) {
        System.out.println(string);
    }
}
