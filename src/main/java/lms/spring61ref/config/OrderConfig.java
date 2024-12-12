package lms.spring61ref.config;

import lms.spring61ref.data.OrderRepository;
import lms.spring61ref.order.OrderService;
import lms.spring61ref.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

    @Bean
    public OrderService orderService(PlatformTransactionManager transactionManager){
        return new OrderServiceImpl(orderRepository() , transactionManager);
    }
    @Bean
    public OrderRepository orderRepository(){
        return new OrderRepository();
    }

}
