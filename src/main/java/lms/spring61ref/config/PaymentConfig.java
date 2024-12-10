package lms.spring61ref.config;

import lms.spring61ref.exrate.RestTemplateExRateProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lms.spring61ref.payment.ExRateProvider;
import lms.spring61ref.payment.PaymentService;
import org.springframework.web.client.RestTemplate;
import java.time.Clock;

@Configuration
public class PaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new RestTemplateExRateProvider(restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }


}
