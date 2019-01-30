package design_patterns.simpleFactory;

/**
 * 根据所需要的产品类型创建不同的产品
 * 这种写法如果有很多的产品类型，或者产品创建时所需要初始化很复杂的话，会导致这个类很庞大。可以使用反射机制进行简化。
 */
public class Factory {
    public Product getProduct(String productType) {
        if ("productionOne".equals(productType)) {
            return new ProductOne();
        } else if ("productionTwo".equals(productType)) {
            return new ProductionTwo();
        } else {
            return null;
        }
    }
}
// 采用反射机制创建所需的产品
class Factory1{
    public Product getProduct(String productName) {
        Product product = null;
        try {
            product = (Product) Class.forName(productName).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  product;
    }
}
