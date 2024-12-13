package lms.spring61ref.spring6.config;

import lms.spring61ref.spring6.data.OrderRepository;
import lms.spring61ref.spring6.order.OrderService;
import lms.spring61ref.spring6.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Import(DataConfig.class)
public class OrderConfig {
    @Bean
    public OrderRepository orderRepository(){
        return new OrderRepository();
    }

    @Bean
    public OrderService orderService( OrderRepository orderRepository){
        return new OrderServiceImpl(orderRepository );

    }


}
