package design_patterns.strategy;

public class PromotionCalculationImpl_3 implements PromotionCalculation{
    @Override
    public Order calculate(Order order) {
        order.setOrderPromotion("promotion-3");
        return order;
    }
}
