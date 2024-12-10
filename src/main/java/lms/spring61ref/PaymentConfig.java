package lms.spring61ref;

import lms.spring61ref.api.ApiTemplate;
import lms.spring61ref.api.ErApiExRateExtract;
import lms.spring61ref.api.SimpleApiExecutor;
import lms.spring61ref.exrate.RestTemplateExRateProvider;
import lms.spring61ref.exrate.WebApiExRateProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lms.spring61ref.exrate.CachedExRateProvider;
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
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new RestTemplateExRateProvider(restTemplate());
    }


    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }


}
