package lms.spring61ref.exrate;

import lms.spring61ref.payment.ExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExRateProvider implements ExRateProvider {

    private final ExRateProvider target;
    private BigDecimal cachedExRate;
    private LocalDateTime cachedExpriyTime;

    public CachedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExRate(String currency)  throws IOException {
        if(cachedExRate == null|| cachedExpriyTime.isBefore(LocalDateTime.now())) {
            cachedExRate = this.target.getExRate(currency);
            cachedExpriyTime = LocalDateTime.now().plusSeconds(3);

            System.out.println("Cache Upated: " + cachedExRate);
        }

        return cachedExRate;
    }
}
