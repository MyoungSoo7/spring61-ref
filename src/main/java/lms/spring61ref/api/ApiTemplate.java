package lms.spring61ref.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class ApiTemplate {
    private final ApiExecutor apiExecutor;
    private final ExRateExtrator exRateExtrator;

    public ApiTemplate( ) {
        this.apiExecutor = new HttpClientApiExecutor();
        this.exRateExtrator = new ErApiExRateExtract();
    }

    public ApiTemplate(ApiExecutor apiExecutor, ExRateExtrator exRateExtrator) {
        this.apiExecutor = apiExecutor;
        this.exRateExtrator = exRateExtrator;
    }

    public BigDecimal getExRate(String url) {
        return this.getExRate(url, this.apiExecutor, this.exRateExtrator);
    }
    public BigDecimal getExRate(String url, ApiExecutor apiExecutor) {
        return this.getExRate(url, apiExecutor, this.exRateExtrator);
    }
    public BigDecimal getExRate(String url, ExRateExtrator exRateExtrator) {
        return this.getExRate(url, this.apiExecutor ,exRateExtrator);
    }

    public BigDecimal getExRate(String url, ApiExecutor apiExecutor, ExRateExtrator exRateExtrator) {
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
