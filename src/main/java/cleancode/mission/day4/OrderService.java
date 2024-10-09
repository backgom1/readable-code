package cleancode.mission.day4;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class OrderService {

    private final Logger log = getLogger(this.getClass().getName());

    private final OrderValidation orderValidation = new OrderValidation();

    public String order(Order order) {
        boolean validated;

        try {
            validated = orderValidation.validateOrder(order);
        } catch (OrderException e) {
            validated = false;
            log.info("에러 원인 : " + e.getMessage());
        } catch (Exception e) {
            validated = false;
            log.info("알수 없는 에러 원인 : " + e.getMessage());
        }

        return printOrderValid(validated);
    }

    private String printOrderValid(boolean validated) {
        if (validated) {
            return "유효합니다!";
        } else {
            return "유효하지 않습니다!";
        }
    }
}
