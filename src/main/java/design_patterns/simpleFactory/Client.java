package design_patterns.simpleFactory;

/**
 * 客户端：用来调用工厂类的方法生产所需要的产品
 */
public class Client {
    public static void main(String[] args) {
        // 沒有采用反射机制的工厂
        Factory factory = new Factory();
        // 获得产品一
        Product productOne = factory.getProduct("productionOne");
        String introductionOne =  productOne.isFunction("这个是产品一");
        System.out.println(introductionOne);
        // 获得产品二
        String introductionTwo = factory.getProduct("productionTwo").isFunction("这个是产品二");
        System.out.println(introductionTwo);
        System.out.println("---------------------------------------------------");
        // 采用反射机制的工厂
        Factory1 factory1 = new Factory1();
        Product productOne1 = factory1.getProduct("design_patterns.simpleFactory.ProductOne");
        System.out.println(productOne1.isFunction("这是由反射型工厂创建的产品一"));
        Product productTwo1 = factory1.getProduct("design_patterns.simpleFactory.ProductionTwo");
        System.out.println(productTwo1.isFunction("这是由反射型工厂创建的产品二"));

    }



}
