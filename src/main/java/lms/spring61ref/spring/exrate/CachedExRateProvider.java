package lms.spring61ref.spring.exrate;

import lms.spring61ref.spring.payment.ExRateProvider;
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
    public BigDecimal getExRate(String currency)   {
        if(cachedExRate == null|| cachedExpriyTime.isBefore(LocalDateTime.now())) {
            cachedExRate = this.target.getExRate(currency);
            cachedExpriyTime = LocalDateTime.now().plusSeconds(3);

            System.out.println("Cache Upated: " + cachedExRate);
        }

        return cachedExRate;
    }
}
