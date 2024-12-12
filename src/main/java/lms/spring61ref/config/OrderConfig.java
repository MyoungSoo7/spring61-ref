package lms.spring61ref.config;

import lms.spring61ref.data.OrderRepository;
import lms.spring61ref.order.OrderService;
import lms.spring61ref.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
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
