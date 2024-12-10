package lms.spring61ref.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lms.spring61ref.order.Order;

import java.math.BigDecimal;

public class OrderRepository {
    private EntityManagerFactory entityManagerFactory;
    public OrderRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void save(Order order) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        em.persist(order);

        em.getTransaction().commit();
        em.close();
    }
}
