package lms.spring61ref.spring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lms.spring61ref.spring.order.Order;


public class OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Order order) {
        entityManager.persist(order);
    }
}
