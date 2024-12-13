package lms.spring61ref.prototype;

import lms.spring61ref.prototype.config.PaymentConfig;
import lms.spring61ref.prototype.payment.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.math.BigDecimal;

public class Client {

    public static void main(String[] args)    {
        BeanFactory beanFactory =  new AnnotationConfigApplicationContext(PaymentConfig.class);
        PaymentService paymentService =  beanFactory.getBean(PaymentService.class);
        System.out.println(paymentService.prepare(100L, "USD", new BigDecimal("50.7")));
    }
}
