package design_patterns.strategy;

public class Main {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        Order order = new Order();
        order.setOrderNo("order-001");
        System.out.println(orderService.prepareOrder1(order, "promotion-1"));
        System.out.println(orderService.prepareOrder2(order, "promotion-2"));
        System.out.println(orderService.prepareOrder3(order, "promotion-3"));
        System.out.println(orderService.prepareOrder4(order, "promotion-4"));
    }
}
