package lms.spring61ref.order;

import lms.spring61ref.data.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PlatformTransactionManager transactionManager;

    public OrderServiceImpl(OrderRepository orderRepository, PlatformTransactionManager transactionManager) {
        this.orderRepository = orderRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public Order createOrder(String no, BigDecimal total){
        Order order = new Order(no, total);
        return new TransactionTemplate(transactionManager).execute(status -> {
            this.orderRepository.save(order);
            return order;
        });
    }

    @Override
    public List<Order> listOrders(List<OrderReq> reqs){
        return new TransactionTemplate(transactionManager).execute(status ->
                reqs.stream().map(req -> createOrder(req.no(), req.total())).toList()
            //reqs.forEach(req -> this.orderRepository.save(new Order(req.no(), req.total())));
        );
    }

}
