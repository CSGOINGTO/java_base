package design_patterns.decorator;

/**
 * 被装饰的类
 */
public class ConcreteComponent implements Component{

    @Override
    public void methodA() {
        System.out.println("methodA");
    }

    @Override
    public void methodB() {
        System.out.println("methodB");
    }
}
