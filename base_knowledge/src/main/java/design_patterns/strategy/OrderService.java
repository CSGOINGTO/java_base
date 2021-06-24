package design_patterns.strategy;

import java.util.Objects;

/**
 * 策略模式：根据不同的需求选择不同的策略。目的为了减少代码的变动量，在新增策略时，只需要改动新增策略的那部分，而不需要变动原代码。
 * 比如示例中：OrderService属于业务处理的部分，在prepareOrder1中，生成策略的代码与OrderService紧耦合，在新增策略时只能在OrderService中新增。
 * prepareOrder2中，虽然将策略抽象成一个类，但是在新增策略时还是需要在OrderService中新增。
 * prepareOrder3中，将策略生成单独抽象成一个工厂类，在新增策略时虽然不需要在OrderService中新增，但是在策略生成工厂中依然需要新增处理代码。
 * prepareOrder4中，在策略生成工厂中，将系统中所有的策略都初始化到一个容器中，新增策略可以动态新增，策略生成工厂中不需要新增处理代码，只需要将新增的策略类新增完毕即可。
 */
public class OrderService {

    private final PromotionCalculationFactory promotionCalculationFactory = new PromotionCalculationFactory();

    public Order prepareOrder1(Order order, String promotion) {
        switch (promotion) {
            case "promotion-1":
                return calPromotion1(order);
            case "promotion-2":
                return calPromotion2(order);
            case "promotion-3":
                return calPromotion3(order);
            case "promotion-4":
                return calPromotion4(order);
        }
        return order;
    }

    public Order prepareOrder2(Order order, String promotion) {
        switch (promotion) {
            case "promotion-1":
                return new PromotionCalculationImpl_1().calculate(order);
            case "promotion-2":
                return new PromotionCalculationImpl_2().calculate(order);
            case "promotion-3":
                return new PromotionCalculationImpl_3().calculate(order);
            case "promotion-4":
                return new PromotionCalculationImpl_4().calculate(order);
        }
        return order;
    }

    public Order prepareOrder3(Order order, String promotion) {
        return Objects.requireNonNull(promotionCalculationFactory.getPromotionCalculation(promotion)).calculate(order);
    }

    public Order prepareOrder4(Order order, String promotion) {
        return promotionCalculationFactory.getPromotionCalculation1(promotion).calculate(order);
    }



    private Order calPromotion1(Order order) {
        order.setOrderPromotion("promotion-1");
        return order;
    }

    private Order calPromotion2(Order order) {
        order.setOrderPromotion("promotion-2");
        return order;
    }

    private Order calPromotion3(Order order) {
        order.setOrderPromotion("promotion-3");
        return order;
    }

    private Order calPromotion4(Order order) {
        order.setOrderPromotion("promotion-4");
        return order;
    }
}
