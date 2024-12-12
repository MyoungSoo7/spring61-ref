package lms.spring61ref;

import lms.spring61ref.config.OrderConfig;
import lms.spring61ref.order.Order;
import lms.spring61ref.order.OrderServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.math.BigDecimal;

public class DataClient {

    public static void main(String[] args) throws Exception {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
        OrderServiceImpl orderServiceImpl = beanFactory.getBean(OrderServiceImpl.class);
        //-Dfile.encoding="UTF-8" -Dsun.stderr.encoding="UTF-8" -Dsun.stdout.encoding="UTF-8"
        System.out.println("한글");
        Order order = orderServiceImpl.createOrder("100", BigDecimal.TEN);
        System.out.println(order);

        /*try {
            new TransactionTemplate(transactionManager).execute(status -> {
                Order order = new Order("100", BigDecimal.TEN);
                orderRepository.save(order);
                System.out.println(order);

                try {
                    System.setOut(new PrintStream(System.out, true, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }

                return null;
            });
        }
        catch (DataIntegrityViolationException e) {
            System.out.println("주문번호 중복 복구 작업 ");
        }*/
    }
}
