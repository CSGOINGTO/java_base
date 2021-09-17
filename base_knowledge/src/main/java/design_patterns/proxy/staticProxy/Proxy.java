package design_patterns.proxy.staticProxy;

public class Proxy implements Subject {

    private Subject realSubject;

    public Proxy() {
        this.realSubject = new RealSubject("被代理人");
    }

    @Override
    public void setName(String name) {
        realSubject.setName(name);
    }

    @Override
    public String getSubjectName() {
        return realSubject.getSubjectName();
    }

    @Override
    public void print(String string) {
        realSubject.print(string);
    }
}
