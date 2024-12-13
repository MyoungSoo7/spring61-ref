package lms.spring61ref.prototype.payment;

import lms.spring61ref.prototype.config.PaymentConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PaymentConfig.class})
class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    void prepare()  {

        Payment usd = paymentService.prepare(1L, "USD", BigDecimal.valueOf(10));

        BigDecimal foreignCurrencyAmount = usd.getForeignCurrencyAmount();
        BigDecimal exRate = usd.getExRate();
        BigDecimal convertedAmount = usd.getConvertedAmount();

        assertThat(usd.getExRate()).isNotNull();
        assertThat(foreignCurrencyAmount.multiply(exRate)).isEqualByComparingTo(convertedAmount);


    }




}