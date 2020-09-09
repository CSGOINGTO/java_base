package design_patterns.strategy;

public class PromotionCalculationImpl_4 implements PromotionCalculation{
    @Override
    public Order calculate(Order order) {
        order.setOrderPromotion("promotion-4");
        return order;
    }
}
