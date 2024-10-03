package cleancode.mission.day4;

import java.util.List;

public class OrderApp {

    private static final OrderService orderService = new OrderService();

    public static void main(String[] args) {
        Order order1 = new Order(List.of("치킨,피자"), 40000, "이은성");
        String orderService1 = orderService.order(order1);
        System.out.println("order1 = " + orderService1);

        Order order2 = new Order(List.of("치킨,피자"), -1, "이은성");
        String orderService2 = orderService.order(order2);
        System.out.println("order2 = " + orderService2);

        Order order3 = new Order(List.of(), 40000, "이은성");
        String orderService3 = orderService.order(order3);
        System.out.println("order3 = " + orderService3);

    }
}
