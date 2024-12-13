package lms.spring61ref.spring6.order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order createOrder(String no, BigDecimal total);

    List<Order> listOrders(List<OrderReq> reqs);
}
