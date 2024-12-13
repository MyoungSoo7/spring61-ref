package lms.spring61ref.spring6.payment;

import java.math.BigDecimal;

public interface ExRateProvider {
    BigDecimal getExRate(String currency)  ;
}
