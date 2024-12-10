package lms.spring61ref.exrate;

import lms.spring61ref.api.ApiTemplate;
import lms.spring61ref.payment.ExRateProvider;
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

