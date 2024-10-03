package cleancode.mission.day4;

public class OrderValidation {

    public boolean validateOrder(Order order) {

        if (order.existCustomerInfo()) {
            throw new OrderException("사용자 정보가 없습니다.");
        }

        if (order.isTotalPricePositive()) {
            throw new OrderException("올바르지 않은 총 가격입니다.");
        }

        if (order.isEmpty()) {
            throw new OrderException("주문 항목이 없습니다.");
        }

        return true;
    }

}
