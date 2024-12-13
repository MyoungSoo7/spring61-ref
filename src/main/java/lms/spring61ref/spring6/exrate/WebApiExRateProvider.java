package lms.spring61ref.spring6.exrate;

import lms.spring61ref.spring6.api.ApiTemplate;
import lms.spring61ref.spring6.payment.ExRateProvider;
import java.math.BigDecimal;

public class WebApiExRateProvider implements ExRateProvider {
    private final ApiTemplate apiTemplate;

    public WebApiExRateProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/"+ currency;
        return apiTemplate.getExRate(url);
    }

}

