package design_patterns.strategy;

import java.util.HashMap;
import java.util.Map;

public class PromotionCalculationFactory {

    private static final Map<String, PromotionCalculation> promotionCalculationMap = new HashMap<>();

    public void init() {
        synchronized (promotionCalculationMap) {
            if (promotionCalculationMap.size() == 0) {
                promotionCalculationMap.put("promotion-1", new PromotionCalculationImpl_1());
                promotionCalculationMap.put("promotion-2", new PromotionCalculationImpl_2());
                promotionCalculationMap.put("promotion-3", new PromotionCalculationImpl_3());
                promotionCalculationMap.put("promotion-4", new PromotionCalculationImpl_4());
            }
        }
    }

    public PromotionCalculation getPromotionCalculation(String promotion) {
        switch (promotion) {
            case "promotion-1":
                return new PromotionCalculationImpl_1();
            case "promotion-2":
                return new PromotionCalculationImpl_2();
            case "promotion-3":
                return new PromotionCalculationImpl_3();
            case "promotion-4":
                return new PromotionCalculationImpl_4();
        }
        return null;
    }

    /**
    将系统中所有的策略使用容器管理，在使用时根据key获取对应的策略。容器可以为spring容器、数据库、缓存或者java容器。
     */
    public PromotionCalculation getPromotionCalculation1(String promotion) {
        init();
        return promotionCalculationMap.get(promotion);
    }
}
