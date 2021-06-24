package design_patterns.decorator;

public class Main {
    public static void main(String[] args) {
        // 1。生成被装饰类
        Component concreteComponent = new ConcreteComponent();
        // 2. 使用装饰类将被装饰类进行装饰。此时被装饰类只被DecoratorA修饰
        concreteComponent = new DecoratorA(concreteComponent);
        concreteComponent.methodA();
        concreteComponent.methodB();

        // 3. 此时被装饰类被DecoratorA和DecoratorB修饰
        concreteComponent = new DecoratorB(concreteComponent);
        concreteComponent.methodA();
        concreteComponent.methodB();
    }
}
