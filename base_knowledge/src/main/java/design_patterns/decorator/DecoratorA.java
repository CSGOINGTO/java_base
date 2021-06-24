package design_patterns.decorator;

public class DecoratorA extends Decorator{

    public DecoratorA(Component component) {
        super(component);
    }

    @Override
    public void methodA() {
        System.out.println("我是装饰类A!");
        System.out.println("开始装饰methodA...");
        super.methodA();
        System.out.println("结束装饰methodA...");
    }

    @Override
    public void methodB() {
        System.out.println("我是装饰类A！");
        System.out.println("开始装饰methodB...");
        super.methodB();
        System.out.println("结束装饰methodB...");
    }
}
