package design_patterns.decorator;

/**
 * 装饰类
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 注意：装饰类中的构造器中参数类型是装饰类和被装饰类的公共接口，因此装饰类可以叠加装饰的功能。  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
public abstract class Decorator implements Component{

    // 被装饰的对象
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void methodA() {
        this.component.methodA();
    }

    @Override
    public void methodB() {
        this.component.methodB();
    }
}
