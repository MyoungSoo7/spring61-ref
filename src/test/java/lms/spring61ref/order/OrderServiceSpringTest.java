package lms.spring61ref.order;

import lms.spring61ref.config.OrderConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {OrderConfig.class})
public class OrderServiceSpringTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void createOrder() {
        var order = orderService.createOrder("100", BigDecimal.TEN);
        assertThat(order.getId()).isGreaterThan(0);
    }

}
