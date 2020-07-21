package design_patterns.strategy;

public class Order {
    private String orderNo;

    private String orderPromotion;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderPromotion() {
        return orderPromotion;
    }

    public void setOrderPromotion(String orderPromotion) {
        this.orderPromotion = orderPromotion;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNo='" + orderNo + '\'' +
                ", orderPromotion='" + orderPromotion + '\'' +
                '}';
    }
}
