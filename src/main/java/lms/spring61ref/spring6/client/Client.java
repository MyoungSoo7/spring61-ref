package lms.spring61ref.spring6.client;

import lms.spring61ref.spring6.config.PaymentConfig;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import lms.spring61ref.spring6.payment.Payment;
import lms.spring61ref.spring6.payment.PaymentService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {

        // IOC
        BeanFactory objectFactory =  new AnnotationConfigApplicationContext(PaymentConfig.class);
        // DI
        PaymentService paymentService = objectFactory.getBean(PaymentService.class);
        // IOC,DI 스프링 컨테이너
//        PaymentService paymentService2 = objectFactory.getBean(PaymentService.class);
//        System.out.println(paymentService == paymentService2); // true

        // Scope : 클래스 상속 (팩토리, 어댑터, 인터프리터, 템플릿) , 오브젝트 합성,
        // 목적
        // 생성 : 팩토리, 추상화, 빌더, 프로토, 싱글톤
        // 구조 : 어댑터, 브리지, 콤포지트, 데코레이터(오브젝트에 부가적인 기능/책임을 동적으로 부여), 플라이웨이트 ,프록시
        // 행위 : Chain of Responsibility, Command, Interpreter, Iterator, Mediator, Memento, Observer, State, Strategy, Template Method, Visitor

        Payment payment1 = paymentService.prepare(100L, "USD", new BigDecimal("50.7"));
        System.out.println(payment1);
        System.out.println("=====================================");
        TimeUnit.SECONDS.sleep(1);
        Payment payment2 = paymentService.prepare(100L, "USD", new BigDecimal("50.7"));
        System.out.println(payment2);
        TimeUnit.SECONDS.sleep(2);
        System.out.println("=====================================");
        Payment payment3 = paymentService.prepare(100L, "USD", new BigDecimal("50.7"));
        TimeUnit.SECONDS.sleep(3);

        System.out.println(payment3);
        System.out.println("=====================================");


    }
}
