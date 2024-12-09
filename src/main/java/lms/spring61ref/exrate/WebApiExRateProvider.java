package lms.spring61ref.exrate;

import lms.spring61ref.api.ApiTemplate;
import lms.spring61ref.api.ErApiExRateExtract;
import lms.spring61ref.api.HttpClientApiExecutor;
import lms.spring61ref.payment.ExRateProvider;
import java.math.BigDecimal;

public class WebApiExRateProvider implements ExRateProvider {
    ApiTemplate apiTemplate = new ApiTemplate();

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/"+ currency;
        return apiTemplate.getExRate(url, new HttpClientApiExecutor(), new ErApiExRateExtract());
    }

}

