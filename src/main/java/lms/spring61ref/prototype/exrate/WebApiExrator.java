package lms.spring61ref.prototype.exrate;

import lms.spring61ref.prototype.api.ApiTemplate;
import lms.spring61ref.prototype.api.ErApiExRateExtract;
import lms.spring61ref.prototype.payment.ExRateProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class WebApiExrator implements ExRateProvider {
    private final ApiTemplate apiTemplate;
    private final ErApiExRateExtract erApiExRateExtract;

    @Override
    public BigDecimal getExRate(String currency)  {
        String url = "https://open.er-api.com/v6/latest/"+currency;
        String apiResponse = apiTemplate.getApi(url);
        BigDecimal exRate= erApiExRateExtract.getValue(apiResponse);
        return exRate;
    }

}
