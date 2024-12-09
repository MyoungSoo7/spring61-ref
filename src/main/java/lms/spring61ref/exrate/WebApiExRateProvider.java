package lms.spring61ref.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import lms.spring61ref.api.ApiExecutor;
import lms.spring61ref.api.ErApiExRateExtract;
import lms.spring61ref.api.ExRateExtrator;
import lms.spring61ref.api.SimpleApiExecutor;
import lms.spring61ref.payment.ExRateProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class WebApiExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency)   {
        String url = "https://open.er-api.com/v6/latest/"+ currency;
        return runApiForExRate(url, new SimpleApiExecutor(),  new ErApiExRateExtract());
    }

    private static BigDecimal runApiForExRate(String url, ApiExecutor apiExecutor , ExRateExtrator exRateExtrator) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = apiExecutor.executeApi(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return exRateExtrator.extractExRate(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
