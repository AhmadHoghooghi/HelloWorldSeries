package ir.asta.training.exercise2.features.orders.dao;

import ir.asta.training.exercise2.entities.OrderEntity;
import ir.asta.training.exercise2.utils.exceptions.EComEntityNotFoundException;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Named("orderDao")
public class OrderDao {

    @PersistenceContext
    private EntityManager em;

    public void save(OrderEntity entity) {
        em.persist(entity);
    }

    public OrderEntity find(Long id) {
        OrderEntity entity = em.find(OrderEntity.class, id);
        if (entity == null) {
            throw new EComEntityNotFoundException(id, OrderEntity.class);
        }
        return entity;
    }

    public List<OrderEntity> list() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<OrderEntity> criteriaQuery = criteriaBuilder.createQuery(OrderEntity.class);
        Root<OrderEntity> order = criteriaQuery.from(OrderEntity.class);

        CriteriaQuery<OrderEntity> filteredQuery = criteriaQuery.select(order);
        TypedQuery<OrderEntity> typedQuery = em.createQuery(filteredQuery);
        List<OrderEntity> resultList = typedQuery.getResultList();
        return resultList;
    }

    public void update(OrderEntity entity) {
        em.merge(entity);
    }

    public void delete(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<OrderEntity> delete = cb.createCriteriaDelete(OrderEntity.class);
        Root<OrderEntity> e = delete.from(OrderEntity.class);
        delete.where(cb.equal(e.get("id"), id));
        Query query = em.createQuery(delete);
        int num = query.executeUpdate();
        if (num == 0) {
            throw new EComEntityNotFoundException(id, OrderEntity.class);
        }
    }

    public List<OrderEntity> search() {
        throw new RuntimeException("Not implemented yet");
        //todo implement search method in CategoryDao.
    }
}
