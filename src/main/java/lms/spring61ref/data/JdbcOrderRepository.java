package lms.spring61ref.data;

import jakarta.annotation.PostConstruct;
import lms.spring61ref.order.Order;
import lms.spring61ref.order.OrderRepository;
import org.springframework.jdbc.core.simple.JdbcClient;

public class JdbcOrderRepository implements OrderRepository {
    private final JdbcClient jdbcClient;

    public JdbcOrderRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @PostConstruct
    void initDb() {
        jdbcClient.sql("""
                create table orders (id bigint not null, no varchar(255), total numeric(38,2), primary key (id));
                alter table if exists orders drop constraint if exists UK43egxxciqr9ncgmxbdx2avi8n;
                alter table if exists orders add constraint UK43egxxciqr9ncgmxbdx2avi8n unique (no);
                create sequence order_id start with 1 increment by 50;
                """).update();
    }

    @Override
    public void save(Order order) {
        Long id = jdbcClient.sql("select next value for order_id").query(Long.class).single();
        order.setId(id);
        jdbcClient.sql("insert into orders (id, no, total) values (:id, :no, :total)")
                .params(order)
                .update();
        System.out.println(id);
    }
}
