package lms.spring61ref.prototype.payment;

import java.math.BigDecimal;

public interface ExRateProvider {
    BigDecimal getExRate(String currency)  ;
}
