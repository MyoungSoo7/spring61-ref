package lms.spring61ref.spring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
public class ApiTemplate {
    private final ApiExecutor apiExecutor;
    private final ExRateExtrator exRateExtrator;

    public BigDecimal getExRate(String url) {
        return this.getExRate(url, this.apiExecutor, this.exRateExtrator);
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
