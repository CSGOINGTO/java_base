package design_patterns.strategy;

public class PromotionCalculationImpl_1 implements PromotionCalculation{

    @Override
    public Order calculate(Order order) {
        order.setOrderPromotion("promotion-1");
        return order;
    }
}
