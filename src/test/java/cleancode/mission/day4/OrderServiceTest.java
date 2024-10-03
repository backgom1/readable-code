package cleancode.mission.day4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderServiceTest {


    @Test
    @DisplayName("유효성 처리 동작 - 정상")
    void test01() {

        //given
        Order order = new Order(List.of("치킨, 피자, 족발"), 40000, "이은성");

        //when
        OrderService orderService = new OrderService();
        String checkOrder = orderService.order(order);

        //then
        assertEquals(checkOrder, "유효합니다!");

    }

    @Test
    @DisplayName("유효성 처리 동작 예외 1- 사용자 정보가 없습니다.")
    void test02() {

        //given
        Order order = new Order(List.of("치킨, 피자, 족발"), 40000, null);

        //when
        OrderValidation orderValidation = new OrderValidation();

        //then
        OrderException orderException = assertThrows(OrderException.class,
                () -> orderValidation.validateOrder(order));

        assertEquals("사용자 정보가 없습니다.",orderException.getMessage());
    }

    @Test
    @DisplayName("유효성 처리 동작 예외 2 - 올바르지 않은 총 가격입니다.")
    void test03() {

        //given
        Order order = new Order(List.of("치킨, 피자, 족발"), -1, "이은성");

        //when
        OrderValidation orderValidation = new OrderValidation();

        //then
        OrderException orderException = assertThrows(OrderException.class,
                () -> orderValidation.validateOrder(order));

        assertEquals("올바르지 않은 총 가격입니다.",orderException.getMessage());

    }

    @Test
    @DisplayName("유효성 처리 동작 예외 3 - 주문 항목이 없습니다.")
    void test04() {

        //given
        Order order = new Order(List.of(), 40000, "이은성");

        //when
        OrderValidation orderValidation = new OrderValidation();

        //then
        OrderException orderException = assertThrows(OrderException.class,
                () -> orderValidation.validateOrder(order));

        assertEquals("주문 항목이 없습니다.",orderException.getMessage());

    }

}