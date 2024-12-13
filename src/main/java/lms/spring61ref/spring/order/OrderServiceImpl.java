package lms.spring61ref.spring.order;

import jakarta.transaction.Transactional;
import lms.spring61ref.spring.data.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }

    @Override

    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);
        this.orderRepository.save(order);
        return order;

    }

    @Override
    public List<Order> listOrders(List<OrderReq> reqs) {
        return  reqs.stream().map(req -> createOrder(req.no(), req.total())).toList();

    }

}
