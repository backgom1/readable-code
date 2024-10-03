package cleancode.mission.day4;

import java.util.List;

public class Order {

    private List<String> items;
    private int totalPrice;
    private String customerInfo;

    public Order(List<String> items, int totalPrice, String customerInfo) {
        this.items = items;
        this.totalPrice = totalPrice;
        this.customerInfo = customerInfo;
    }

    public boolean existCustomerInfo() {
        return customerInfo == null || customerInfo.isEmpty();
    }

    public boolean isTotalPricePositive() {
        return totalPrice <= 0;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
