package lms.spring61ref.order;

import lms.spring61ref.config.OrderConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {OrderConfig.class})
public class OrderServiceSpringTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    DataSource dataSource;

    @Test
    public void createOrder() {
        var order = orderService.createOrder("100", BigDecimal.TEN);
        assertThat(order.getId()).isGreaterThan(0);
    }

    @Test
    void createOrders(){
        List<OrderReq> orderReqs = List.of(
                new OrderReq("101", BigDecimal.TEN),
                new OrderReq("102", BigDecimal.TEN)
        );

        var orders = orderService.listOrders(orderReqs);

        assertThat(orders).hasSize(2);
        orders.forEach(order -> assertThat(order.getId()).isGreaterThan(0) );

    }

    @Test
    void createDuplicatedOrders(){
        List<OrderReq> orderReqs = List.of(
                new OrderReq("101", BigDecimal.TEN),
                new OrderReq("101", BigDecimal.TEN)
        );

        assertThatThrownBy(() ->orderService.listOrders(orderReqs)).isInstanceOf(DataIntegrityViolationException.class);
        //orders.forEach(order -> assertThat(order.getId()).isGreaterThan(0) );

        JdbcClient client = JdbcClient.create(dataSource);
        var count = client.sql("select count(*) from orders where no ='101'").query(Long.class).single();
        assertThat(count).isEqualTo(0);

    }

}
