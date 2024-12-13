package lms.spring61ref.spring6.exrate;

import lms.spring61ref.spring6.payment.ExRateProvider;
import java.math.BigDecimal;

public class SimpleExRatePaymentService implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency)    {
        if(currency.equals("USD")) return new BigDecimal("1000");
        throw new IllegalArgumentException("지원되지 않는 통화입니다.");
    }
}
