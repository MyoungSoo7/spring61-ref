package lms.spring61ref.spring.payment;

import java.math.BigDecimal;

public interface ExRateProvider {
    BigDecimal getExRate(String currency)  ;
}
