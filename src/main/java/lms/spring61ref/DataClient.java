package lms.spring61ref;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lms.spring61ref.data.OrderRepository;
import lms.spring61ref.order.Order;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class DataClient {

    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository orderRepository = beanFactory.getBean(OrderRepository.class);

        Order order = new Order("100",BigDecimal.TEN);
        orderRepository.save(order);

        System.out.println(order);
    }



}
