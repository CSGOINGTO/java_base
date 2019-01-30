package design_patterns.simpleFactory;

public class ProductOne implements Product {
    @Override
    public String isFunction(String introduction) {
        return "productionOne" + introduction;
    }
}
