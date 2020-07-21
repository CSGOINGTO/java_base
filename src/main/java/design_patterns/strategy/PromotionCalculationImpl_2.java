package design_patterns.strategy;

public class PromotionCalculationImpl_2 implements PromotionCalculation{
    @Override
    public Order calculate(Order order) {
        order.setOrderPromotion("promotion-2");
        return order;
    }
}
