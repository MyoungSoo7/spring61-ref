package lms.spring61ref.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lms.spring61ref.order.Order;

public class OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Order order) {
        entityManager.persist(order);
    }
}
