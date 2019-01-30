package design_patterns.simpleFactory;

public class ProductionTwo implements Product {
    @Override
    public String isFunction(String introduction) {
        return "productionTwo" + introduction;
    }
}
