package lms.spring61ref.spring.exrate;

import lms.spring61ref.spring.api.ApiTemplate;
import lms.spring61ref.spring.payment.ExRateProvider;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;

@RequiredArgsConstructor
public class WebApiExRateProvider implements ExRateProvider {
    private final ApiTemplate apiTemplate;

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/"+ currency;
        return apiTemplate.getExRate(url);
    }

}

